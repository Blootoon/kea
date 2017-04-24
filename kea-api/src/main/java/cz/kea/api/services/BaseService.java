package cz.kea.api.services;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import cz.kea.api.model.Identifiable;
import cz.kea.api.utils.Filter;
import cz.kea.api.utils.Order;
import cz.kea.api.utils.Page;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
public interface BaseService<T extends Identifiable<ID>, ID extends Serializable> {

    <E extends T> E save(E model);

    <E extends T> E saveAndFlush(E model);

    <E extends T> List<E> save(Iterable<E> models);

    void delete(ID id);

    T findOne(ID id);

    T findOne(Map<Filter, Object> filter);

    List<T> findAll();

    List<T> findAll(Order... orders);

    Page<T> findAll(int offset, int limit);

    Page<T> findAll(int offset, int limit, Order... orders);

    List<T> findAll(Map<Filter, Object> filter);

    Page<T> findAll(Map<Filter, Object> Filter, int offset, int limit);

    List<T> findAll(Map<Filter, Object> Filter, Order... orders);

    Page<T> findAll(Map<Filter, Object> Filter, int offset, int limit, Order... orders);

    long count();

    long count(Map<Filter, Object> filter);

}
