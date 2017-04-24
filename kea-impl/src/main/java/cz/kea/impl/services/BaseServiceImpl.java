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
import org.apache.log4j.LogMF;
import org.apache.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
public abstract class BaseServiceImpl<T extends Identifiable<ID>, ID extends Serializable> implements BaseService<T, ID> {

    protected final Logger LOG = Logger.getLogger(getClass());

    @Override
    public <E extends T> E save(E model) {
        Validate.notNull(model);

        LogMF.debug(LOG, "Saving model {0}.", new Object[]{model});

        return getRepository().save(model);
    }

    @Override
    public <E extends T> E saveAndFlush(E model) {
        Validate.notNull(model);

        LogMF.debug(LOG, "Saving and flushing model {0}.", new Object[]{model});

        return getRepository().saveAndFlush(model);
    }

    @Override
    public <E extends T> List<E> save(Iterable<E> models) {
        Validate.notNull(models);

        LogMF.debug(LOG, "Saving models {0}.", new Object[]{models});

        return getRepository().save(models);
    }

    @Override
    public void delete(ID id) {
        Validate.notNull(id);

        LogMF.debug(LOG, "Deleting model by id = {0}.", new Object[]{id});

        getRepository().delete(id);
    }

    @Override
    public T findOne(ID id) {
        Validate.notNull(id);

        LogMF.debug(LOG, "Finding model by id = {0}.", new Object[]{id});

        return getRepository().findOne(id);
    }

    @Override
    public T findOne(Map<Filter, Object> filter) {
        Validate.notNull(filter);

        LogMF.debug(LOG, "Finding model with filter = {0}.", new Object[]{filter});

        return getRepository().findOne(getSpecificationFactory().createSpecification(filter));
    }

    @Override
    public List<T> findAll() {
        LogMF.debug(LOG, "Finding all models.", new Object[]{});

        return getRepository().findAll();
    }

    @Override
    public List<T> findAll(Order... orders) {
        Validate.notNull(orders);

        LogMF.debug(LOG, "Finding all models with orders = {0}.", new Object[]{Arrays.toString(orders)});

        return getRepository().findAll(convertSort(orders));
    }

    @Override
    public cz.kea.api.utils.Page<T> findAll(int offset, int limit) {
        LogMF.debug(LOG, "Finding all models by offset = {0}, limit = {1}.", new Object[]{offset, limit});

        return convertPage(getRepository().findAll(new SliceRequest(offset, limit)));
    }

    @Override
    public cz.kea.api.utils.Page<T> findAll(int offset, int limit, Order... orders) {
        LogMF.debug(LOG, "Finding all models by offset = {0}, limit = {1}, orders = {2}.", new Object[]{offset, limit, orders});

        return convertPage(getRepository().findAll(new SliceRequest(offset, limit)));
    }

    @Override
    public List<T> findAll(Map<Filter, Object> filter) {
        Validate.notNull(filter);

        LogMF.debug(LOG, "Finding all models by filter = {0}.", new Object[]{filter});

        return getRepository().findAll(getSpecificationFactory().createSpecification(filter));
    }

    @Override
    public cz.kea.api.utils.Page<T> findAll(Map<Filter, Object> filter, int offset, int limit) {
        Validate.notNull(filter);

        LogMF.debug(LOG, "Finding all models by example = {0}, offset = {1}, limit = {2}.", new Object[]{filter, offset, limit});

        return convertPage(getRepository().findAll(getSpecificationFactory().createSpecification(filter), new SliceRequest(offset, limit)));
    }

    @Override
    public List<T> findAll(Map<Filter, Object> filter, Order... orders) {
        Validate.notNull(filter);
        Validate.notNull(orders);

        LogMF.debug(LOG, "Finding all models by filter = {0}, offset = {1}, limit = {2}, orders = {3}.", new Object[]{filter, Arrays.toString(orders)});

        return getRepository().findAll(getSpecificationFactory().createSpecification(filter), convertSort(orders));
    }

    @Override
    public cz.kea.api.utils.Page<T> findAll(Map<Filter, Object> filter, int offset, int limit, Order... orders) {
        Validate.notNull(filter);
        Validate.notNull(orders);

        LogMF.debug(LOG, "Finding all models by filter = {0}, offset = {1}, limit = {2}, orders = {3}.", new Object[]{filter, offset, limit, Arrays.toString(orders)});

        return convertPage(getRepository().findAll(getSpecificationFactory().createSpecification(filter), new SliceRequest(offset, limit, convertSort(orders))));
    }

    @Override
    public long count() {
        LogMF.debug(LOG, "Counting all models.", new Object[]{});

        return getRepository().count();
    }

    @Override
    public long count(Map<Filter, Object> filter) {
        Validate.notNull(filter);

        LogMF.debug(LOG, "Counting all models by filter = {0}.", new Object[]{filter});

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
