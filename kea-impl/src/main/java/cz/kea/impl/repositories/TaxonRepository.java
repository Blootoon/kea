package cz.kea.impl.repositories;

import cz.kea.impl.entities.TaxonEntity;
import org.springframework.stereotype.Repository;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
@Repository
public interface TaxonRepository extends BaseHierarchicalRepository<TaxonEntity, Long> {
}
