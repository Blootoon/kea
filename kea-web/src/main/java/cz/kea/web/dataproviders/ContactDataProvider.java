package cz.kea.web.dataproviders;

import cz.kea.api.model.Contact;
import cz.kea.api.services.BaseService;
import cz.kea.api.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
@Component
public class ContactDataProvider extends BaseDataProvider<Contact, Long> {

    @Autowired
    protected ContactService contactService;

    @Override
    protected BaseService<Contact, Long> getService() {
        return contactService;
    }
}
