package cz.kea.api.services;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import cz.kea.api.model.Identifiable;
import cz.kea.api.utils.Filter;
import cz.kea.api.utils.Order;
import cz.kea.api.utils.Page;

/**
 * Base service interface for common repository operations.
 *
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
public interface BaseService<T extends Identifiable<ID>, ID extends Serializable> {

    <E extends T> E save(E model);

    <E extends T> E saveAndFlush(E model);

    <E extends T> List<E> save(Iterable<E> models);

    void delete(ID id);

    T findOne(ID id);

    T findOne(ID id, boolean initDependencies);

    T findOne(Map<Filter, Object> filter);

    T findOne(Map<Filter, Object> filter, boolean initDependencies);

    List<T> findAll();

    List<T> findAll(boolean initDependencies);

    List<T> findAll(Order... orders);

    List<T> findAll(Order[] orders, boolean initDependencies);

    Page<T> findAll(int offset, int limit);

    Page<T> findAll(int offset, int limit, boolean initDependencies);

    Page<T> findAll(int offset, int limit, Order... orders);

    Page<T> findAll(int offset, int limit, Order[] orders, boolean initDependencies);

    List<T> findAll(Map<Filter, Object> filter);

    List<T> findAll(Map<Filter, Object> filter, boolean initDependencies);

    Page<T> findAll(Map<Filter, Object> filter, int offset, int limit);

    Page<T> findAll(Map<Filter, Object> filter, int offset, int limit, boolean initDependencies);

    List<T> findAll(Map<Filter, Object> filter, Order... orders);

    List<T> findAll(Map<Filter, Object> filter, Order[] orders, boolean initDependencies);

    Page<T> findAll(Map<Filter, Object> filter, int offset, int limit, Order... orders);

    Page<T> findAll(Map<Filter, Object> filter, int offset, int limit, Order[] orders, boolean initDependencies);

    long count();

    long count(Map<Filter, Object> filter);

}
