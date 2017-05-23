package cz.kea.api.services;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import cz.kea.api.model.HierarchicalModel;
import cz.kea.api.utils.Filter;
import cz.kea.api.utils.Order;
import cz.kea.api.utils.Page;

/**
 * Base service interface for common hierarchical repository operations.
 *
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
public interface BaseHierarchicalService<T extends HierarchicalModel<ID>, ID extends Serializable> extends BaseService<T, ID> {

    boolean hasChildren(T model);

    boolean hasChildren(T model, Map<Filter, Object> filter);

    long countChildren(T model);

    long countChildren(T model, Map<Filter, Object> filter);

    List<T> getChildren(T model);

    List<T> getChildren(T model, boolean initDependencies);

    List<T> getChildren(T model, Order... orders);

    List<T> getChildren(T model, Order[] orders, boolean initDependencies);

    Page<T> getChildren(T model, int offset, int limit);

    Page<T> getChildren(T model, int offset, int limit, boolean initDependencies);

    Page<T> getChildren(T model, int offset, int limit, Order... orders);

    Page<T> getChildren(T model, int offset, int limit, Order[] orders, boolean initDependencies);

    List<T> getChildren(T model, Map<Filter, Object> filter);

    List<T> getChildren(T model, Map<Filter, Object> filter, boolean initDependencies);

    Page<T> getChildren(T model, Map<Filter, Object> filter, int offset, int limit);

    Page<T> getChildren(T model, Map<Filter, Object> filter, int offset, int limit, boolean initDependencies);

    List<T> getChildren(T model, Map<Filter, Object> filter, Order... orders);

    List<T> getChildren(T model, Map<Filter, Object> filter, Order[] orders, boolean initDependencies);

    Page<T> getChildren(T model, Map<Filter, Object> filter, int offset, int limit, Order... orders);

    Page<T> getChildren(T model, Map<Filter, Object> filter, int offset, int limit, Order[] orders, boolean initDependencies);
}
