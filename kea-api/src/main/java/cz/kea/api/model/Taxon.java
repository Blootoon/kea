package cz.kea.api.model;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
public interface Taxon extends HierarchicalModel<Long> {

    void setId(Long id);

    String getLatinName();

    void setLatinName(String latinName);

    String getEnglishName();

    void setEnglishName(String englishName);

    String getGermanName();

    void setGermanName(String germanName);

    String getCzechName();

    void setCzechName(String czechName);
}
