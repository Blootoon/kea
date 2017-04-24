package cz.kea.impl.factories.model;

import java.time.LocalDate;
import java.util.List;

import cz.kea.api.factories.model.NestFactory;
import cz.kea.api.model.Bird;
import cz.kea.api.model.Nest;
import cz.kea.api.model.Pair;
import cz.kea.impl.entities.NestEntity;
import org.springframework.stereotype.Component;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
@Component
public class NestFactoryImpl implements NestFactory {

    @Override
    public Nest createNest() {
        return new NestEntity();
    }

    @Override
    public Nest createNest(Pair pair, LocalDate date) {
        return new NestEntity(pair, date);
    }

    @Override
    public Nest createNest(Pair pair, LocalDate date, List<Bird> chicks, String note) {
        return new NestEntity(pair, date, chicks, note);
    }
}
