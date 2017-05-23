package cz.kea.impl.services;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
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
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

/**
 * Base service implementation for common repository operations.
 *
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
@Transactional
public abstract class BaseServiceImpl<T extends Identifiable<ID>, ID extends Serializable> implements BaseService<T, ID> {

    protected final Logger log = LogManager.getFormatterLogger(getClass());

    @Override
    public <E extends T> E save(E model) {
        Validate.notNull(model);

        log.debug("Saving model %s.", model);

        E savedModel = getRepository().save(model);

        log.debug("Model %s saved.", model);

        return savedModel;
    }

    @Override
    public <E extends T> E saveAndFlush(E model) {
        Validate.notNull(model);

        log.debug("Saving and flushing model %s.", model);

        E savedModel = getRepository().saveAndFlush(model);

        log.debug("Model %s saved.", model);

        return savedModel;
    }

    @Override
    public <E extends T> List<E> save(Iterable<E> models) {
        Validate.notNull(models);

        log.debug("Saving models %s.", models);

        List<E> savedModels = getRepository().save(models);

        log.debug("%d models saved.", savedModels.size());

        return savedModels;
    }

    @Override
    public void delete(ID id) {
        Validate.notNull(id);

        log.debug("Deleting model by id = %s.", id);

        getRepository().delete(id);

        log.debug("Model deleted.");
    }

    @Override
    public T findOne(ID id) {
        return findOne(id, false);
    }

    @Override
    public T findOne(ID id, boolean initDependencies) {
        Validate.notNull(id);

        log.debug("Finding model by id = %s.", id);

        T model = getRepository().findOne(id);

        log.debug("Model found: %s.", model);

        if (initDependencies) {
            initModel(model);
        }

        return model;
    }

    @Override
    public T findOne(Map<Filter, Object> filter) {
        return findOne(filter, false);
    }

    @Override
    public T findOne(Map<Filter, Object> filter, boolean initDependencies) {
        Validate.notNull(filter);

        log.debug("Finding model with filter = %s.", filter);

        Specification<T> specification = getSpecificationFactory().createSpecification(filter);

        T model = getRepository().findOne(specification);

        log.debug("Model found: %s.", model);

        if (initDependencies) {
            initModel(model);
        }

        return model;
    }

    @Override
    public List<T> findAll() {
        return findAll(false);
    }

    @Override
    public List<T> findAll(boolean initDependencies) {
        log.debug("Finding all models.");

        List<T> models = getRepository().findAll();

        log.debug("%d models found.", models.size());

        if (initDependencies) {
            initModels(models);
        }

        return models;
    }

    @Override
    public List<T> findAll(Order... orders) {
        return findAll(orders, false);
    }

    @Override
    public List<T> findAll(Order[] orders, boolean initDependencies) {
        Validate.notNull(orders);

        log.debug("Finding all models with orders = %s.", Arrays.toString(orders));

        Sort sort = convertSort(orders);

        List<T> models = getRepository().findAll(sort);

        log.debug("%d models found.", models.size());

        if (initDependencies) {
            initModels(models);
        }

        return models;
    }

    @Override
    public cz.kea.api.utils.Page<T> findAll(int offset, int limit) {
        return findAll(offset, limit, false);
    }

    @Override
    public cz.kea.api.utils.Page<T> findAll(int offset, int limit, boolean initDependencies) {
        log.debug("Finding all models by offset = %d, limit = %d.", offset, limit);

        Pageable pageable = new SliceRequest(offset, limit);

        cz.kea.api.utils.Page<T> models = convertPage(getRepository().findAll(pageable));

        log.debug("%d models found.", models.getItems().size());

        if (initDependencies) {
            initModels(models.getItems());
        }

        return models;
    }

    @Override
    public cz.kea.api.utils.Page<T> findAll(int offset, int limit, Order... orders) {
        return findAll(offset, limit, orders, false);
    }

    @Override
    public cz.kea.api.utils.Page<T> findAll(int offset, int limit, Order[] orders, boolean initDependencies) {
        log.debug("Finding all models by offset = %d, limit = %d, orders = %s.", offset, limit, orders);

        Sort sort = convertSort(orders);
        Pageable pageable = new SliceRequest(offset, limit, sort);

        cz.kea.api.utils.Page<T> models = convertPage(getRepository().findAll(pageable));

        log.debug("%d models found.", models.getItems().size());

        if (initDependencies) {
            initModels(models.getItems());
        }

        return models;
    }

    @Override
    public List<T> findAll(Map<Filter, Object> filter) {
        return findAll(filter, false);
    }

    @Override
    public List<T> findAll(Map<Filter, Object> filter, boolean initDependencies) {
        Validate.notNull(filter);

        log.debug("Finding all models by filter = %s.", filter);

        Specification<T> specification = getSpecificationFactory().createSpecification(filter);

        List<T> models = getRepository().findAll(specification);

        log.debug("%d models found.", models.size());

        if (initDependencies) {
            initModels(models);
        }

        return models;
    }

    @Override
    public cz.kea.api.utils.Page<T> findAll(Map<Filter, Object> filter, int offset, int limit) {
        return findAll(filter, offset, limit, false);
    }

    @Override
    public cz.kea.api.utils.Page<T> findAll(Map<Filter, Object> filter, int offset, int limit, boolean initDependencies) {
        Validate.notNull(filter);

        log.debug("Finding all models by filter = %s, offset = %d, limit = %d.", filter, offset, limit);

        Specification<T> specification = getSpecificationFactory().createSpecification(filter);
        Pageable pageable = new SliceRequest(offset, limit);

        cz.kea.api.utils.Page<T> models = convertPage(getRepository().findAll(specification, pageable));

        log.debug("%d models found.", models.getItems().size());

        if (initDependencies) {
            initModels(models.getItems());
        }

        return models;
    }

    @Override
    public List<T> findAll(Map<Filter, Object> filter, Order... orders) {
        return findAll(filter, orders, false);
    }

    @Override
    public List<T> findAll(Map<Filter, Object> filter, Order[] orders, boolean initDependencies) {
        Validate.notNull(filter);
        Validate.notNull(orders);

        log.debug("Finding all models by filter = %s, offset = %d, limit = %d, orders = %s.", filter, Arrays.toString(orders));

        Specification<T> specification = getSpecificationFactory().createSpecification(filter);
        Sort sort = convertSort(orders);

        List<T> models = getRepository().findAll(specification, sort);

        log.debug("%d models found.", models.size());

        if (initDependencies) {
            initModels(models);
        }

        return models;
    }

    @Override
    public cz.kea.api.utils.Page<T> findAll(Map<Filter, Object> filter, int offset, int limit, Order... orders) {
        return findAll(filter, offset, limit, orders, false);
    }

    @Override
    public cz.kea.api.utils.Page<T> findAll(Map<Filter, Object> filter, int offset, int limit, Order[] orders, boolean initDependencies) {
        Validate.notNull(filter);
        Validate.notNull(orders);

        log.debug("Finding all models by filter = %s, offset = %d, limit = %d, orders = %s.", filter, offset, limit, Arrays.toString(orders));

        Specification<T> specification = getSpecificationFactory().createSpecification(filter);
        Sort sort = convertSort(orders);
        Pageable pageable = new SliceRequest(offset, limit, sort);

        cz.kea.api.utils.Page<T> models = convertPage(getRepository().findAll(specification, pageable));

        log.debug("%d models found.", models.getItems().size());

        if (initDependencies) {
            initModels(models.getItems());
        }

        return models;
    }

    @Override
    public long count() {
        log.debug("Counting all models.");

        long count = getRepository().count();

        log.debug("%d models found.", count);

        return count;
    }

    @Override
    public long count(Map<Filter, Object> filter) {
        Validate.notNull(filter);

        log.debug("Counting all models by filter = %s.", filter);

        Specification<T> specification = getSpecificationFactory().createSpecification(filter);

        long count = getRepository().count(specification);

        log.debug("%d models found.", count);

        return count;
    }

    /**
     * Performs an initialization of lazy loaded dependencies. Should be overridden in subclasses.
     * @param model
     */
    protected void initModel(T model) {
        Validate.notNull(model);

        log.trace("Initializing lazy loaded dependencies of model %s.", model);
    }

    /**
     * Initializes collection of models with lazy loaded dependencies.
     * @see BaseServiceImpl#initModel(cz.kea.api.model.Identifiable)
     * @param models colleaction of models
     */
    protected void initModels(Collection<T> models) {
        Validate.notNull(models);

        models.stream().forEach(model -> initModel(model));
    }

    /**
     * Converts Kea orders to Spring specific sorts.
     * @param orders Kea orders
     * @return Spring sorts
     */
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
