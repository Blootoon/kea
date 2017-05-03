package cz.kea.api.factories.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import cz.kea.api.model.Bird;
import cz.kea.api.model.WeightRecord;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
public interface WeightRecordFactory {

    WeightRecord createWeightRecord();

    WeightRecord createWeightRecord(Bird bird, LocalDate date, BigDecimal weight);
}
