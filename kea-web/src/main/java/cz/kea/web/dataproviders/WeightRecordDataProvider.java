package cz.kea.web.dataproviders;

import cz.kea.api.model.WeightRecord;
import cz.kea.api.services.BaseService;
import cz.kea.api.services.WeightRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
@Component
public class WeightRecordDataProvider extends BaseDataProvider<WeightRecord, Long> {

    @Autowired
    protected WeightRecordService weightRecordService;

    @Override
    protected BaseService<WeightRecord, Long> getService() {
        return weightRecordService;
    }
}
