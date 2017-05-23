package cz.kea.api.factories.model;

import cz.kea.api.model.Taxon;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
public interface TaxonFactory {

    Taxon createTaxon();

    Taxon createTaxon(Taxon parent, String latinName, String englishName, String germanName, String czechName);
}
