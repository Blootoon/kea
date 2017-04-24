package cz.kea.web.dataproviders;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.vaadin.data.provider.AbstractBackEndDataProvider;
import com.vaadin.data.provider.Query;
import com.vaadin.data.provider.QuerySortOrder;
import cz.kea.api.enums.SortDirection;
import cz.kea.api.model.Identifiable;
import cz.kea.api.services.BaseService;
import cz.kea.api.utils.Filter;
import cz.kea.api.utils.Order;
import org.apache.log4j.LogMF;
import org.apache.log4j.Logger;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
public abstract class BaseDataProvider<T extends Identifiable<ID>, ID extends Serializable> extends AbstractBackEndDataProvider<T, Map<Filter, Object>> {

    private final Logger LOG = Logger.getLogger(getClass());

    @Override
    protected Stream<T> fetchFromBackEnd(Query<T, Map<Filter, Object>> query) {
        LogMF.trace(LOG, "Fetching from back end.", new Object[]{});

        Optional<Map<Filter, Object>> optionalFilter = query.getFilter();
        Map<Filter, Object> filter = optionalFilter.orElse(Collections.emptyMap());
        Order[] orders = convertOrders(query.getSortOrders());
        if (orders.length > 0) {
            return getService().findAll(filter, query.getOffset(), query.getLimit(), orders).getItems().stream();
        } else {
            return getService().findAll(filter, query.getOffset(), query.getLimit()).getItems().stream();
        }
    }

    @Override
    protected int sizeInBackEnd(Query<T, Map<Filter, Object>> query) {
        LogMF.trace(LOG, "Getting size from back end.", new Object[]{});

        Optional<Map<Filter, Object>> optionalFilter = query.getFilter();
        Map<Filter, Object> filter = optionalFilter.orElse(Collections.emptyMap());
        return (int) getService().count(filter);
    }

    protected Order[] convertOrders(List<QuerySortOrder> querySortOrder) {
        return querySortOrder.stream().map((s) -> {
            switch (s.getDirection()) {
                case DESCENDING:
                    return new Order(s.getSorted(), SortDirection.DESC);
                default:
                    return new Order(s.getSorted(), SortDirection.ASC);
            }
        }).collect(Collectors.toList()).toArray(new Order[0]);
    }

    protected abstract BaseService<T, ID> getService();
}
