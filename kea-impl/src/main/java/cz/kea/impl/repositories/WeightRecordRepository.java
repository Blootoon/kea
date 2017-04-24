package cz.kea.impl.repositories;

import cz.kea.impl.entities.WeightRecordEntity;
import org.springframework.stereotype.Repository;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
@Repository
public interface WeightRecordRepository extends BaseRepository<WeightRecordEntity, Long> {
}
