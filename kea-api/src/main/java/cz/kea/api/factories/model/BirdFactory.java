package cz.kea.api.factories.model;

import java.time.LocalDate;

import cz.kea.api.enums.Genus;
import cz.kea.api.enums.Sex;
import cz.kea.api.enums.Species;
import cz.kea.api.enums.State;
import cz.kea.api.model.Bird;
import cz.kea.api.model.Contact;
import cz.kea.api.model.Nest;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
public interface BirdFactory {

    Bird createBird(Genus genus, Species species, Sex sex, State state);

    Bird createBird(Genus genus, Species species, Sex sex, State state, Nest nest, LocalDate layed, LocalDate hatched, String mutation, String identification, String name, Contact owner, String note);
}
