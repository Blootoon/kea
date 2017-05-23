package cz.kea.web.components.dataproviders;

import cz.kea.api.model.Nest;
import cz.kea.api.services.BaseService;
import cz.kea.api.services.NestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
@Component
public class NestDataProvider extends BaseDataProvider<Nest, Long> {

    @Autowired
    protected NestService nestService;

    @Override
    protected BaseService<Nest, Long> getService() {
        return nestService;
    }
}
