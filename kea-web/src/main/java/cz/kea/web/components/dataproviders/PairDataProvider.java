package cz.kea.web.components.dataproviders;

import cz.kea.api.model.Pair;
import cz.kea.api.services.BaseService;
import cz.kea.api.services.PairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
@Component
public class PairDataProvider extends BaseDataProvider<Pair, Long> {

    @Autowired
    protected PairService pairService;

    @Override
    protected BaseService<Pair, Long> getService() {
        return pairService;
    }
}
