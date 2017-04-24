package cz.kea.impl.services;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import cz.kea.api.model.Identifiable;
import cz.kea.api.services.BaseService;
import cz.kea.api.utils.Filter;
import cz.kea.api.utils.Order;
import cz.kea.impl.factories.specifications.SpecificationFactory;
import cz.kea.impl.repositories.BaseRepository;
import cz.kea.impl.utils.SliceRequest;
import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
public abstract class BaseServiceImpl<T extends Identifiable<ID>, ID extends Serializable> implements BaseService<T, ID> {

    protected final Logger log = LogManager.getFormatterLogger(getClass());

    @Override
    public <E extends T> E save(E model) {
        Validate.notNull(model);

        log.debug("Saving model %s.", model);

        return getRepository().save(model);
    }

    @Override
    public <E extends T> E saveAndFlush(E model) {
        Validate.notNull(model);

        log.debug("Saving and flushing model %s.", model);

        return getRepository().saveAndFlush(model);
    }

    @Override
    public <E extends T> List<E> save(Iterable<E> models) {
        Validate.notNull(models);

        log.debug("Saving models %s.", models);

        return getRepository().save(models);
    }

    @Override
    public void delete(ID id) {
        Validate.notNull(id);

        log.debug("Deleting model by id = %s.", id);

        getRepository().delete(id);
    }

    @Override
    public T findOne(ID id) {
        Validate.notNull(id);

        log.debug("Finding model by id = %s.", id);

        return getRepository().findOne(id);
    }

    @Override
    public T findOne(Map<Filter, Object> filter) {
        Validate.notNull(filter);

        log.debug("Finding model with filter = %s.", filter);

        return getRepository().findOne(getSpecificationFactory().createSpecification(filter));
    }

    @Override
    public List<T> findAll() {
        log.debug("Finding all models.");

        return getRepository().findAll();
    }

    @Override
    public List<T> findAll(Order... orders) {
        Validate.notNull(orders);

        log.debug("Finding all models with orders = %s.", Arrays.toString(orders));

        return getRepository().findAll(convertSort(orders));
    }

    @Override
    public cz.kea.api.utils.Page<T> findAll(int offset, int limit) {
        log.debug("Finding all models by offset = %d, limit = %d.", offset, limit);

        return convertPage(getRepository().findAll(new SliceRequest(offset, limit)));
    }

    @Override
    public cz.kea.api.utils.Page<T> findAll(int offset, int limit, Order... orders) {
        log.debug("Finding all models by offset = %d, limit = %d, orders = %s.", offset, limit, orders);

        return convertPage(getRepository().findAll(new SliceRequest(offset, limit)));
    }

    @Override
    public List<T> findAll(Map<Filter, Object> filter) {
        Validate.notNull(filter);

        log.debug("Finding all models by filter = %s.", filter);

        return getRepository().findAll(getSpecificationFactory().createSpecification(filter));
    }

    @Override
    public cz.kea.api.utils.Page<T> findAll(Map<Filter, Object> filter, int offset, int limit) {
        Validate.notNull(filter);

        log.debug("Finding all models by filter = %s, offset = %d, limit = %d.", filter, offset, limit);

        return convertPage(getRepository().findAll(getSpecificationFactory().createSpecification(filter), new SliceRequest(offset, limit)));
    }

    @Override
    public List<T> findAll(Map<Filter, Object> filter, Order... orders) {
        Validate.notNull(filter);
        Validate.notNull(orders);

        log.debug("Finding all models by filter = %s, offset = %d, limit = %d, orders = %s.", filter, Arrays.toString(orders));

        return getRepository().findAll(getSpecificationFactory().createSpecification(filter), convertSort(orders));
    }

    @Override
    public cz.kea.api.utils.Page<T> findAll(Map<Filter, Object> filter, int offset, int limit, Order... orders) {
        Validate.notNull(filter);
        Validate.notNull(orders);

        log.debug("Finding all models by filter = %s, offset = %d, limit = %d, orders = %s.", filter, offset, limit, Arrays.toString(orders));

        return convertPage(getRepository().findAll(getSpecificationFactory().createSpecification(filter), new SliceRequest(offset, limit, convertSort(orders))));
    }

    @Override
    public long count() {
        log.debug("Counting all models.");

        return getRepository().count();
    }

    @Override
    public long count(Map<Filter, Object> filter) {
        Validate.notNull(filter);

        log.debug("Counting all models by filter = %s.", filter);

        return getRepository().count(getSpecificationFactory().createSpecification(filter));
    }

    protected Sort convertSort(Order... orders) {
        List<Sort.Order> sortOrders = Arrays.asList(orders).stream()
            .map((o) -> new Sort.Order(Sort.Direction.valueOf(o.getSortDirection().name()), o.getColumn()))
            .collect(Collectors.toList());
        return new org.springframework.data.domain.Sort(sortOrders);
    }

    protected cz.kea.api.utils.Page<T> convertPage(Page<T> source) {
        return new cz.kea.api.utils.Page<>(source.getTotalElements(), source.getContent(), source.hasPrevious(), source.hasNext());
    }

    protected abstract <E extends T> BaseRepository<E, ID> getRepository();

    protected abstract <E extends T> SpecificationFactory<E, ID> getSpecificationFactory();
}
