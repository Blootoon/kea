package cz.kea.api.factories.model;

import java.time.LocalDate;
import java.util.List;

import cz.kea.api.model.Bird;
import cz.kea.api.model.Nest;
import cz.kea.api.model.Pair;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
public interface PairFactory {

    Pair createPair();

    Pair createPair(Bird male, Bird female);

    Pair createPair(Bird male, Bird female, List<Nest> nests, LocalDate dateFrom, LocalDate dateTo, String note);
}
