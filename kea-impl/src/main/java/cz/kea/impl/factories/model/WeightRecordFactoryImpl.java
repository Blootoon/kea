package cz.kea.impl.factories.model;

import java.time.LocalDate;

import cz.kea.api.factories.model.WeightRecordFactory;
import cz.kea.api.model.Bird;
import cz.kea.api.model.WeightRecord;
import cz.kea.impl.entities.WeightRecordEntity;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
public class WeightRecordFactoryImpl implements WeightRecordFactory {

    @Override
    public WeightRecord createWeightRecord() {
        return new WeightRecordEntity();
    }

    @Override
    public WeightRecord createWeightRecord(Bird bird, LocalDate date, double weight) {
        return new WeightRecordEntity(bird, date, weight);
    }
}
