package cz.kea.impl.repositories;

import cz.kea.impl.entities.ContactEntity;
import org.springframework.stereotype.Repository;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
@Repository
public interface ContactRepository extends BaseRepository<ContactEntity, Long> {
}
