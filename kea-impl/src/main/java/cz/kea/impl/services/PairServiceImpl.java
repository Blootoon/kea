package cz.kea.impl.services;

import cz.kea.api.model.Pair;
import cz.kea.api.services.PairService;
import cz.kea.impl.entities.PairEntity;
import cz.kea.impl.factories.specifications.PairSpecificationFactory;
import cz.kea.impl.factories.specifications.SpecificationFactory;
import cz.kea.impl.repositories.BaseRepository;
import cz.kea.impl.repositories.PairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
@Service
@Transactional
public class PairServiceImpl extends BaseServiceImpl<Pair, Long> implements PairService {

    @Autowired
    private PairRepository pairRepository;

    @Autowired
    private PairSpecificationFactory pairSpecificationFactory;

    @Override
    public Pair create() {
        return new PairEntity();
    }

    @Override
    protected BaseRepository<PairEntity, Long> getRepository() {
        return pairRepository;
    }

    @Override
    protected SpecificationFactory<PairEntity, Long> getSpecificationFactory() {
        return pairSpecificationFactory;
    }
}
