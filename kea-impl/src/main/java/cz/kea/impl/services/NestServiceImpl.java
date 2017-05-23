package cz.kea.impl.services;

import cz.kea.api.model.Nest;
import cz.kea.api.services.NestService;
import cz.kea.impl.entities.NestEntity;
import cz.kea.impl.factories.specifications.NestSpecificationFactory;
import cz.kea.impl.factories.specifications.SpecificationFactory;
import cz.kea.impl.repositories.BaseRepository;
import cz.kea.impl.repositories.NestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
@Service
@Transactional
public class NestServiceImpl extends BaseServiceImpl<Nest, Long> implements NestService {

    @Autowired
    private NestRepository nestRepository;

    @Autowired
    private NestSpecificationFactory nestSpecificationFactory;

    @Override
    public Nest create() {
        return new NestEntity();
    }

    @Override
    protected BaseRepository<NestEntity, Long> getRepository() {
        return nestRepository;
    }

    @Override
    protected SpecificationFactory<NestEntity, Long> getSpecificationFactory() {
        return nestSpecificationFactory;
    }
}
