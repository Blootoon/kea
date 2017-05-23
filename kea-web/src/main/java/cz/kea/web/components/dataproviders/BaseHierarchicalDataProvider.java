package cz.kea.web.components.dataproviders;

import java.io.Serializable;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import com.vaadin.data.provider.AbstractHierarchicalDataProvider;
import com.vaadin.data.provider.HierarchicalQuery;
import cz.kea.api.model.HierarchicalModel;
import cz.kea.api.services.BaseHierarchicalService;
import cz.kea.api.utils.Filter;
import cz.kea.api.utils.Order;
import cz.kea.web.components.converters.OrderConverter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
public abstract class BaseHierarchicalDataProvider<T extends HierarchicalModel<ID>, ID extends Serializable> extends AbstractHierarchicalDataProvider<T, Map<Filter, Object>> {

    protected final Logger log = LogManager.getFormatterLogger(getClass());

    @Autowired
    protected OrderConverter orderConverter;

    @Override
    public int getChildCount(HierarchicalQuery<T, Map<Filter, Object>> hierarchicalQuery) {
        log.trace("Getting children count from back end.");

        Optional<Map<Filter, Object>> optionalFilter = hierarchicalQuery.getFilter();
        Map<Filter, Object> filter = optionalFilter.orElse(Collections.emptyMap());
        return (int) getService().countChildren(hierarchicalQuery.getParent());
    }

    @Override
    public Stream<T> fetchChildren(HierarchicalQuery<T, Map<Filter, Object>> hierarchicalQuery) {
        log.trace("Fetching children count from back end.");

        Optional<Map<Filter, Object>> optionalFilter = hierarchicalQuery.getFilter();
        Map<Filter, Object> filter = optionalFilter.orElse(Collections.emptyMap());
        Order[] orders = orderConverter.convert(hierarchicalQuery.getSortOrders());
        if (orders.length > 0) {
            return getService().getChildren(hierarchicalQuery.getParent(), hierarchicalQuery.getOffset(), hierarchicalQuery.getLimit(), orders).getItems().stream();
        } else {
            return getService().getChildren(hierarchicalQuery.getParent(), hierarchicalQuery.getOffset(), hierarchicalQuery.getLimit()).getItems().stream();
        }
    }

    @Override
    public boolean hasChildren(T t) {
        log.trace("Determining if model %s has children.", t);

        return getService().hasChildren(t);
    }

    @Override
    public boolean isInMemory() {
        return false;
    }

    protected abstract BaseHierarchicalService<T, ID> getService();
}
