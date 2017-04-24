package cz.kea.impl.factories.model;

import java.time.LocalDate;
import java.util.List;

import cz.kea.api.factories.model.PairFactory;
import cz.kea.api.model.Bird;
import cz.kea.api.model.Nest;
import cz.kea.api.model.Pair;
import cz.kea.impl.entities.PairEntity;
import org.springframework.stereotype.Component;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
@Component
public class PairFactoryImpl implements PairFactory {

    @Override
    public Pair createPair() {
        return new PairEntity();
    }

    @Override
    public Pair createPair(Bird male, Bird female) {
        return new PairEntity(male, female);
    }

    @Override
    public Pair createPair(Bird male, Bird female, List<Nest> nests, LocalDate dateFrom, LocalDate dateTo, String note) {
        return new PairEntity(male, female, nests, dateFrom, dateTo, note);
    }
}
