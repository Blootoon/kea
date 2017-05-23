package cz.kea.api.factories.model;

import cz.kea.api.model.User;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
public interface UserFactory {

    User createUser();

    User createUser(String firstName, String lastName, String email, String phone);
}
