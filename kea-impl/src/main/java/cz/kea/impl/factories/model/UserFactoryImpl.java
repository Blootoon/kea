package cz.kea.impl.factories.model;

import cz.kea.api.factories.model.UserFactory;
import cz.kea.api.model.User;
import cz.kea.impl.entities.UserEntity;
import org.springframework.stereotype.Component;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
@Component
public class UserFactoryImpl implements UserFactory {

    @Override
    public User createUser() {
        return new UserEntity();
    }

    @Override
    public User createUser(String firstName, String lastName, String email, String phone) {
        return new UserEntity(firstName, lastName, email, phone);
    }
}
