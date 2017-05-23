package cz.kea.web.components.dataproviders;

import cz.kea.api.model.Bird;
import cz.kea.api.services.BaseService;
import cz.kea.api.services.BirdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
@Component
public class BirdDataProvider extends BaseDataProvider<Bird, Long> {

    @Autowired
    protected BirdService birdService;

    @Override
    protected BaseService<Bird, Long> getService() {
        return birdService;
    }
}
