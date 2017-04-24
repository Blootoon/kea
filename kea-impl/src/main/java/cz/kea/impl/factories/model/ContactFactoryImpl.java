package cz.kea.impl.factories.model;

import java.util.List;

import cz.kea.api.factories.model.ContactFactory;
import cz.kea.api.model.Bird;
import cz.kea.api.model.Contact;
import cz.kea.impl.entities.ContactEntity;
import org.springframework.stereotype.Component;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
@Component
public class ContactFactoryImpl implements ContactFactory {

    @Override
    public Contact createContact() {
        return new ContactEntity();
    }

    @Override
    public Contact createContact(String firstName, String lastName, String email, String phone) {
        return new ContactEntity(firstName, lastName, email, phone);
    }

    @Override
    public Contact createContact(String firstName, String lastName, String email, String phone, List<Bird> birds, String note) {
        return new ContactEntity(firstName, lastName, email, phone, birds, note);
    }
}
