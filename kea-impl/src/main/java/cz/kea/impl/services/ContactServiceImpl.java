package cz.kea.impl.services;

import cz.kea.api.model.Contact;
import cz.kea.api.services.ContactService;
import cz.kea.impl.entities.ContactEntity;
import cz.kea.impl.factories.specifications.ContactSpecificationFactory;
import cz.kea.impl.factories.specifications.SpecificationFactory;
import cz.kea.impl.repositories.BaseRepository;
import cz.kea.impl.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
@Service
@Transactional
public class ContactServiceImpl extends BaseServiceImpl<Contact, Long> implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private ContactSpecificationFactory contactSpecificationFactory;

    protected BaseRepository<ContactEntity, Long> getRepository() {
        return contactRepository;
    }

    @Override
    protected SpecificationFactory<ContactEntity, Long> getSpecificationFactory() {
        return contactSpecificationFactory;
    }
}
