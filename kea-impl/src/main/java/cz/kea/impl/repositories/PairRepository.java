package cz.kea.impl.repositories;

import cz.kea.impl.entities.PairEntity;
import org.springframework.stereotype.Repository;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
@Repository
public interface PairRepository extends BaseRepository<PairEntity, Long> {
}
