package cz.kea.web.components;

import cz.kea.api.model.Contact;
import org.springframework.stereotype.Component;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
@Component
public class ContactHelper {

    public String nameGenerator(Contact contact) {
        if (contact == null) {
            return null;
        } else {
            return contact.getFullName();
        }
    }
}
