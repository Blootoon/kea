package cz.kea.web.converters;

import java.util.List;
import java.util.stream.Collectors;

import com.vaadin.data.provider.QuerySortOrder;
import cz.kea.api.enums.SortDirection;
import cz.kea.api.utils.Order;
import org.springframework.stereotype.Component;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
@Component
public class OrderConverter {

    public Order[] convert(List<QuerySortOrder> querySortOrder) {
        return querySortOrder.stream().map((s) -> {
            switch (s.getDirection()) {
                case DESCENDING:
                    return new Order(s.getSorted(), SortDirection.DESC);
                default:
                    return new Order(s.getSorted(), SortDirection.ASC);
            }
        }).collect(Collectors.toList()).toArray(new Order[0]);
    }
}
