package cz.kea.impl.factories.model;

import cz.kea.api.factories.model.TaxonFactory;
import cz.kea.api.model.Taxon;
import cz.kea.impl.entities.TaxonEntity;
import org.springframework.stereotype.Component;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
@Component
public class TaxonFactoryImpl implements TaxonFactory {

    @Override
    public Taxon createTaxon() {
        return new TaxonEntity();
    }

    @Override
    public Taxon createTaxon(Taxon parent, String latinName, String englishName, String germanName, String czechName) {
        return new TaxonEntity(parent, latinName, englishName, germanName, czechName);
    }
}
