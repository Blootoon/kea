package cz.kea.api.factories.model;

import java.time.LocalDate;
import java.util.List;

import cz.kea.api.model.Bird;
import cz.kea.api.model.Nest;
import cz.kea.api.model.Pair;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
public interface NestFactory {

    Nest createNest();

    Nest createNest(Pair pair, LocalDate date);

    Nest createNest(Pair pair, LocalDate date, List<Bird> chicks, String note);
}
