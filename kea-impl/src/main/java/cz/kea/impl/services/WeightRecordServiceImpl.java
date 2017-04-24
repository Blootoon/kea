package cz.kea.impl.services;

import cz.kea.api.model.WeightRecord;
import cz.kea.api.services.WeightRecordService;
import cz.kea.impl.entities.WeightRecordEntity;
import cz.kea.impl.factories.specifications.SpecificationFactory;
import cz.kea.impl.factories.specifications.WeightRecordSpecificationFactory;
import cz.kea.impl.repositories.BaseRepository;
import cz.kea.impl.repositories.WeightRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
@Service
@Transactional
public class WeightRecordServiceImpl extends BaseServiceImpl<WeightRecord, Long> implements WeightRecordService {

    @Autowired
    private WeightRecordRepository weightRecordRepository;

    @Autowired
    private WeightRecordSpecificationFactory weightRecordSpecificationFactory;

    protected BaseRepository<WeightRecordEntity, Long> getRepository() {
        return weightRecordRepository;
    }

    @Override
    protected SpecificationFactory<WeightRecordEntity, Long> getSpecificationFactory() {
        return weightRecordSpecificationFactory;
    }
}
