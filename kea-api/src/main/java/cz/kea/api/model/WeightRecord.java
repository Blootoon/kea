package cz.kea.api.model;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
public interface WeightRecord extends Identifiable<Long> {

    void setId(Long id);

    Bird getBird();

    void setBird(Bird bird);

    LocalDate getDate();

    void setDate(LocalDate date);

    BigDecimal getWeight();

    void setWeight(BigDecimal weight);

}
