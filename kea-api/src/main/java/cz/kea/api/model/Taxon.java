package cz.kea.api.model;

import java.util.List;

import cz.kea.api.enums.TaxonomicRank;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
public interface Taxon extends Identifiable<Long> {

    void setId(Long id);

    Taxon getParent();

    void setParent(Taxon parent);

    List<Taxon> getChildren();

    void setChildren(List<Taxon> children);

    TaxonomicRank getTaxonomicRank();

    void setTaxonomicRank(TaxonomicRank taxonomicRank);

    String getLatinName();

    void setLatinName(String latinName);

    String getEnglishName();

    void setEnglishName(String englishName);

    String getGermanName();

    void setGermanName(String germanName);

    String getCzechName();

    void setCzechName(String czechName);

}
