package cz.kea.impl.services;

import cz.kea.api.model.Taxon;
import cz.kea.api.services.BaseHierarchicalService;
import cz.kea.api.services.TaxonService;
import cz.kea.impl.entities.TaxonEntity;
import cz.kea.impl.factories.specifications.SpecificationFactory;
import cz.kea.impl.factories.specifications.TaxonSpecificationFactory;
import cz.kea.impl.repositories.BaseHierarchicalRepository;
import cz.kea.impl.repositories.BaseRepository;
import cz.kea.impl.repositories.TaxonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
@Service
@Transactional
public class TaxonServiceImpl extends BaseHierarchicalServiceImpl<Taxon, Long> implements TaxonService {

    @Autowired
    private TaxonRepository taxonRepository;

    @Autowired
    private TaxonSpecificationFactory taxonSpecificationFactory;

    @Override
    public Taxon create() {
        return new TaxonEntity();
    }

    @Override
    protected BaseHierarchicalRepository<TaxonEntity, Long> getRepository() {
        return taxonRepository;
    }

    @Override
    protected SpecificationFactory<TaxonEntity, Long> getSpecificationFactory() {
        return taxonSpecificationFactory;
    }
}
