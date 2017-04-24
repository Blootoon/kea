package cz.kea.api.model;

import java.time.LocalDate;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
public interface WeightRecord extends Identifiable<Long> {

    Long getId();

    void setId(Long id);

    Bird getBird();

    void setBird(Bird bird);

    LocalDate getDate();

    void setDate(LocalDate date);

    double getWeight();

    void setWeight(double weight);

}
