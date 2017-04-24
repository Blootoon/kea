package cz.kea.api.model;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
public interface Nest extends Identifiable<Long> {

    Long getId();

    void setId(Long id);

    Pair getPair();

    void setPair(Pair pair);

    LocalDate getDate();

    void setDate(LocalDate date);

    List<Bird> getChicks();

    void setChicks(List<Bird> chicks);

    String getNote();

    void setNote(String note);

}
