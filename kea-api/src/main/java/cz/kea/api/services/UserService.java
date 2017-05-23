package cz.kea.api.services;

import cz.kea.api.exceptions.LoginException;
import cz.kea.api.model.User;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
public interface UserService extends BaseService<User, Long> {

    User findByEmail(String email);

    User login(String email, String password) throws LoginException;
}