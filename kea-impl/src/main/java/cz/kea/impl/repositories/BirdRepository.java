package cz.kea.impl.repositories;

import cz.kea.impl.entities.BirdEntity;
import org.springframework.stereotype.Repository;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
@Repository
public interface BirdRepository extends BaseRepository<BirdEntity, Long> {
}
