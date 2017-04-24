package cz.kea.impl.factories.model;

import java.time.LocalDate;

import cz.kea.api.enums.Genus;
import cz.kea.api.enums.Sex;
import cz.kea.api.enums.Species;
import cz.kea.api.enums.State;
import cz.kea.api.factories.model.BirdFactory;
import cz.kea.api.model.Bird;
import cz.kea.api.model.Contact;
import cz.kea.api.model.Nest;
import cz.kea.impl.entities.BirdEntity;
import org.springframework.stereotype.Component;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
@Component
public class BirdFactoryImpl implements BirdFactory {

    @Override
    public Bird createBird(Genus genus, Species species, Sex sex, State state) {
        return new BirdEntity(genus, species, sex, state);
    }

    @Override
    public Bird createBird(Genus genus, Species species, Sex sex, State state, Nest nest, LocalDate layed, LocalDate hatched, String mutation, String identification, String name, Contact owner,
                           String note) {
        return new BirdEntity(genus, species, sex, state, nest, layed, hatched, mutation, identification, name, owner, note);
    }
}
