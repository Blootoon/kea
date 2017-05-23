package cz.kea.impl.services;

import cz.kea.api.services.PasswordEncryptionService;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
@Service
public class PasswordEncryptionServiceImpl implements PasswordEncryptionService {

    @Override
    public String encrypt(String password) {
        return DigestUtils.md5DigestAsHex(password.getBytes());
    }
}
