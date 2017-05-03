package cz.kea.api.model;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
public interface Pair extends Identifiable<Long> {

    void setId(Long id);

    Bird getMale();

    void setMale(Bird male);

    Bird getFemale();

    void setFemale(Bird female);

    List<Nest> getNests();

    void setNests(List<Nest> nests);

    LocalDate getDateFrom();

    void setDateFrom(LocalDate dateFrom);

    LocalDate getDateTo();

    void setDateTo(LocalDate dateTo);

    String getNote();

    void setNote(String note);

}
