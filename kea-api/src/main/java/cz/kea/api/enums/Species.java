package cz.kea.api.enums;

import static cz.kea.api.enums.Genus.*;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
public enum Species {

    ARATINGA_SOLSTITIALIS(ARATINGA),
    FORPUS_COELESTIS(FORPUS);

    private Genus genus;

    Species(Genus genus) {
        this.genus = genus;
    }

    public Genus getGenus() {
        return genus;
    }

    public void setGenus(Genus genus) {
        this.genus = genus;
    }

}
