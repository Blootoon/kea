package cz.kea.web.dataproviders;

import cz.kea.api.model.Taxon;
import cz.kea.api.services.BaseHierarchicalService;
import cz.kea.api.services.TaxonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
@Component
public class TaxonHierarchicalDataProvider extends BaseHierarchicalDataProvider<Taxon, Long> {

    @Autowired
    private TaxonService taxonService;

    @Override
    protected BaseHierarchicalService<Taxon, Long> getService() {
        return taxonService;
    }
}
