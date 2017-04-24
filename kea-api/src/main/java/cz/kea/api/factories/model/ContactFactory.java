package cz.kea.api.factories.model;

import java.util.List;

import cz.kea.api.model.Bird;
import cz.kea.api.model.Contact;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
public interface ContactFactory {

    Contact createContact();

    Contact createContact(String firstName, String lastName, String email, String phone);

    Contact createContact(String firstName, String lastName, String email, String phone, List<Bird> birds, String note);
}
