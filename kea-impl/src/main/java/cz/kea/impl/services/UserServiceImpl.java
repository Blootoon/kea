package cz.kea.impl.services;

import java.time.LocalDateTime;

import cz.kea.api.configuration.ApplicationConfiguration;
import cz.kea.api.exceptions.LoginException;
import cz.kea.api.model.User;
import cz.kea.api.services.PasswordEncryptionService;
import cz.kea.api.services.UserService;
import cz.kea.impl.entities.UserEntity;
import cz.kea.impl.factories.specifications.SpecificationFactory;
import cz.kea.impl.factories.specifications.UserSpecificationFactory;
import cz.kea.impl.repositories.BaseRepository;
import cz.kea.impl.repositories.UserRepository;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
@Service
@Component
public class UserServiceImpl extends BaseServiceImpl<User, Long> implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserSpecificationFactory userSpecificationFactory;

    @Autowired
    private PasswordEncryptionService passwordEncryptionService;

    @Autowired
    private ApplicationConfiguration applicationConfiguration;

    @Override
    public User findByEmail(String email) {
        Validate.notNull(email, "Email should not be null.");

        log.debug("Finding user by email = %s.", email);

        return userRepository.findByEmail(email);
    }

    @Override
    public User login(String email, String password) throws LoginException {
        Validate.notNull(email, "Email should not be null.");
        Validate.notNull(password, "Password should not be null.");

        log.info("User with email = %s tries to log in.", email);

        User user = findByEmail(email);
        if (user != null) {
            LocalDateTime now = LocalDateTime.now();

            // handle login lock
            if (user.isLocked()) {
                int timeout = applicationConfiguration.getLoginFailedAttemptsTimeout();
                LocalDateTime lockExpires = user.getLastLoginAttempt().plusMinutes(timeout);

                log.info("User with email = %s is locked. Lock expires at %s.", email, lockExpires);

                // if is timeout set and lock hasn't expired then trow login exception
                if (timeout > 0 && lockExpires.isBefore(now)) {
                    throw new LoginException(true, lockExpires);
                } else {
                    log.info("Releasing lock for user with email = %s because lock has expired.", email);
                    // otherwise unlock user
                    user.setLocked(false);
                }
            }

            String encryptedPassword = passwordEncryptionService.encrypt(password);

            // handle login
            if (user.getPassword().equals(encryptedPassword)) {
                log.info("User with email = %s authorized successfully.", email);

                // if successful then reset failed attempts
                user.setFailedAttempts(0);
                user.setLastLoginAttempt(now);
                user.setLastLogin(now);

                // save user
                return saveAndFlush(user);
            } else {
                log.info("User with email = %s authorization failed. Wrong password.", email);

                // increment failed attempts
                int maxAttempts = applicationConfiguration.getLoginFailedAttemptsMax();
                int failedAttempts = user.getFailedAttempts();
                failedAttempts++;

                // if max attempts is set and user reached this number then lock his account
                if (maxAttempts > 0 && failedAttempts >= maxAttempts) {
                    user.setLocked(true);
                }

                user.setFailedAttempts(failedAttempts);
                user.setLastLoginAttempt(now);

                // save user
                saveAndFlush(user);

                throw new LoginException();
            }
        } else {
            log.info("User with email = %s does not exist.", email);

            throw new LoginException();
        }
    }

    @Override
    protected BaseRepository<UserEntity, Long> getRepository() {
        return userRepository;
    }

    @Override
    protected SpecificationFactory<UserEntity, Long> getSpecificationFactory() {
        return userSpecificationFactory;
    }
}
