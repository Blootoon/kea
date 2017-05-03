package cz.kea.impl.factories.model;

import java.time.LocalDate;

import cz.kea.api.enums.Sex;
import cz.kea.api.enums.State;
import cz.kea.api.factories.model.BirdFactory;
import cz.kea.api.model.Bird;
import cz.kea.api.model.Contact;
import cz.kea.api.model.Nest;
import cz.kea.api.model.Taxon;
import cz.kea.impl.entities.BirdEntity;
import org.springframework.stereotype.Component;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
@Component
public class BirdFactoryImpl implements BirdFactory {

    @Override
    public Bird createBird() {
        return new BirdEntity();
    }

    @Override
    public Bird createBird(Taxon taxon, Sex sex, State state) {
        return new BirdEntity(taxon, sex, state);
    }

    @Override
    public Bird createBird(Taxon taxon, Sex sex, State state, Nest nest, LocalDate layed, LocalDate hatched, String mutation, String identification, String name, Contact owner,
                           String note) {
        return new BirdEntity(taxon, sex, state, nest, layed, hatched, mutation, identification, name, owner, note);
    }
}
