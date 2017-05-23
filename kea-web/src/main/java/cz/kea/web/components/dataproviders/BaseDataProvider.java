package cz.kea.web.components.dataproviders;

import java.io.Serializable;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import com.vaadin.data.provider.AbstractBackEndDataProvider;
import com.vaadin.data.provider.Query;
import cz.kea.api.model.Identifiable;
import cz.kea.api.services.BaseService;
import cz.kea.api.utils.Filter;
import cz.kea.api.utils.Order;
import cz.kea.web.components.converters.OrderConverter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
public abstract class BaseDataProvider<T extends Identifiable<ID>, ID extends Serializable> extends AbstractBackEndDataProvider<T, Map<Filter, Object>> {

    protected final Logger log = LogManager.getFormatterLogger(getClass());

    @Autowired
    protected OrderConverter orderConverter;

    @Override
    protected Stream<T> fetchFromBackEnd(Query<T, Map<Filter, Object>> query) {
        log.trace("Fetching from back end.");

        Optional<Map<Filter, Object>> optionalFilter = query.getFilter();
        Map<Filter, Object> filter = optionalFilter.orElse(Collections.emptyMap());
        Order[] orders = orderConverter.convert(query.getSortOrders());
        if (orders.length > 0) {
            return getService().findAll(filter, query.getOffset(), query.getLimit(), orders).getItems().stream();
        } else {
            return getService().findAll(filter, query.getOffset(), query.getLimit()).getItems().stream();
        }
    }

    @Override
    protected int sizeInBackEnd(Query<T, Map<Filter, Object>> query) {
        log.trace("Getting size from back end.");

        Optional<Map<Filter, Object>> optionalFilter = query.getFilter();
        Map<Filter, Object> filter = optionalFilter.orElse(Collections.emptyMap());
        return (int) getService().count(filter);
    }

    protected abstract BaseService<T, ID> getService();
}
