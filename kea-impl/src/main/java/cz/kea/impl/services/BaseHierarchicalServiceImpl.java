package cz.kea.impl.services;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import cz.kea.api.model.HierarchicalModel;
import cz.kea.api.services.BaseHierarchicalService;
import cz.kea.api.utils.Filter;
import cz.kea.api.utils.Order;
import cz.kea.api.utils.Page;
import cz.kea.impl.repositories.BaseHierarchicalRepository;
import cz.kea.impl.utils.SliceRequest;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.Validate;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

/**
 * Base service implementation for common hierarchical repository operations.
 *
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
@Transactional
public abstract class BaseHierarchicalServiceImpl<T extends HierarchicalModel<ID>, ID extends Serializable> extends BaseServiceImpl<T, ID> implements BaseHierarchicalService<T, ID> {

    @Override
    public boolean hasChildren(T model) {
        log.debug("Determining if model %s has children.", model);

        return countChildren(model) > 0;
    }

    @Override
    public boolean hasChildren(T model, Map<Filter, Object> filter) {
        log.debug("Determining if model %s has children by filter = %s.", model, filter);

        return countChildren(model, filter) > 0;
    }

    @Override
    public long countChildren(T model) {
        log.debug("Counting model %s children.", model);

        long count;
        if (model == null) {
            count = getRepository().countByParentIsNull();
        } else {
            count = getRepository().countByParent(model);
        }


        log.debug("%d children found.", count);

        return count;
    }

    @Override
    public long countChildren(T model, Map<Filter, Object> filter) {
        Validate.notNull(filter);

        log.debug("Counting model %s children by filter = %s.", model);

        Specification<T> specification = getSpecificationFactory().createSpecification(filter);

        long count;
        if (model == null) {
            count = getRepository().countByParentIsNull(specification);
        } else {
            count = getRepository().countByParent(model, specification);
        }

        log.debug("%d children found.", count);

        return count;
    }

    @Override
    public List<T> getChildren(T model) {
        return getChildren(model, false);
    }

    @Override
    public List<T> getChildren(T model, boolean initDependencies) {
        log.debug("Getting model %s children.", model);

        List<T> children;
        if (model == null) {
            children = getRepository().findByParentIsNull();
        } else {
            children = getRepository().findByParent(model);
        }

        log.debug("%d children found.", children.size());

        if (initDependencies) {
            initModels(children);
        }

        return children;
    }

    @Override
    public List<T> getChildren(T model, Order... orders) {
        return getChildren(model, orders, false);
    }

    @Override
    public List<T> getChildren(T model, Order[] orders, boolean initDependencies) {
        Validate.notNull(orders);

        log.debug("Getting model %s children by orders = %s.", model, orders);

        Sort sort = convertSort(orders);

        List<T> children;
        if (model == null) {
            children = getRepository().findByParentIsNull(sort);
        } else {
            children = getRepository().findByParent(model, sort);
        }

        log.debug("%d children found.", children.size());

        if (initDependencies) {
            initModels(children);
        }

        return children;
    }

    @Override
    public Page<T> getChildren(T model, int offset, int limit) {
        return getChildren(model, offset, limit, false);
    }

    @Override
    public Page<T> getChildren(T model, int offset, int limit, boolean initDependencies) {
        log.debug("Getting model %s children by offset = %d, limit = %d.", model, offset, limit);

        Pageable pageable = new SliceRequest(offset, limit);

        Page<T> children;
        if (model == null) {
            children = convertPage(getRepository().findByParentIsNull(pageable));
        } else {
            children = convertPage(getRepository().findByParent(model, pageable));
        }

        log.debug("%d children found.", children.getItems().size());

        if (initDependencies) {
            initModels(children.getItems());
        }

        return children;
    }

    @Override
    public Page<T> getChildren(T model, int offset, int limit, Order... orders) {
        return getChildren(model, offset, limit, false);
    }

    @Override
    public Page<T> getChildren(T model, int offset, int limit, Order[] orders, boolean initDependencies) {
        Validate.notNull(orders);

        log.debug("Getting model %s children by offset = %d, limit = %d, orders = %s.", model, offset, limit, orders);

        Sort sort = convertSort(orders);
        Pageable pageable = new SliceRequest(offset, limit, sort);

        Page<T> children;
        if (model == null) {
            children = convertPage(getRepository().findByParentIsNull(pageable));
        } else {
            children = convertPage(getRepository().findByParent(model, pageable));
        }

        log.debug("%d children found.", children.getItems().size());

        if (initDependencies) {
            initModels(children.getItems());
        }

        return children;
    }

    @Override
    public List<T> getChildren(T model, Map<Filter, Object> filter) {
        return getChildren(model, filter, false);
    }

    @Override
    public List<T> getChildren(T model, Map<Filter, Object> filter, boolean initDependencies) {
        Validate.notNull(filter);

        log.debug("Getting model %s children by filter = %s.", model, filter);

        Specification<T> specification = getSpecificationFactory().createSpecification(filter);

        List<T> children;
        if (model == null) {
            children = getRepository().findByParentIsNull(specification);
        } else {
            children = getRepository().findByParent(model, specification);
        }

        log.debug("%d children found.", children.size());

        if (initDependencies) {
            initModels(children);
        }

        return children;
    }

    @Override
    public Page<T> getChildren(T model, Map<Filter, Object> filter, int offset, int limit) {
        return getChildren(model, filter, offset, limit, false);
    }

    @Override
    public Page<T> getChildren(T model, Map<Filter, Object> filter, int offset, int limit, boolean initDependencies) {
        Validate.notNull(filter);

        log.debug("Getting model %s children by filter = %s, offset = %d, limit = %d.", model, filter, offset, limit);

        Specification<T> specification = getSpecificationFactory().createSpecification(filter);
        Pageable pageable = new SliceRequest(offset, limit);

        Page<T> children;
        if (model == null) {
            children = convertPage(getRepository().findByParentIsNull(specification, pageable));
        } else {
            children = convertPage(getRepository().findByParent(model, specification, pageable));
        }

        log.debug("%d children found.", children.getItems().size());

        if (initDependencies) {
            initModels(children.getItems());
        }

        return children;
    }

    @Override
    public List<T> getChildren(T model, Map<Filter, Object> filter, Order... orders) {
        return getChildren(model, filter, orders, false);
    }

    @Override
    public List<T> getChildren(T model, Map<Filter, Object> filter, Order[] orders, boolean initDependencies) {
        Validate.notNull(filter);
        Validate.notNull(orders);

        log.debug("Getting model %s children by filter = %s, orders = %s.", model, filter, orders);

        Specification<T> specification = getSpecificationFactory().createSpecification(filter);
        Sort sort = convertSort(orders);

        List<T> children;
        if (model == null) {
            children = getRepository().findByParentIsNull(specification, sort);
        } else {
            children = getRepository().findByParent(model, specification, sort);
        }

        log.debug("%d children found.", children.size());

        if (initDependencies) {
            initModels(children);
        }

        return children;
    }

    @Override
    public Page<T> getChildren(T model, Map<Filter, Object> filter, int offset, int limit, Order... orders) {
        return getChildren(model, filter, offset, limit, orders, false);
    }

    @Override
    public Page<T> getChildren(T model, Map<Filter, Object> filter, int offset, int limit, Order[] orders, boolean initDependencies) {
        Validate.notNull(filter);
        Validate.notNull(orders);

        log.debug("Getting model %s children by filter = %s, offset = %d, limit = %d, orders = %s.", model, filter, offset, limit, orders);

        Specification<T> specification = getSpecificationFactory().createSpecification(filter);
        Sort sort = convertSort(orders);
        Pageable pageable = new SliceRequest(offset, limit, sort);

        Page<T> children;
        if (model == null) {
            children = convertPage(getRepository().findByParentIsNull(specification, pageable));
        } else {
            children = convertPage(getRepository().findByParent(model, specification, pageable));
        }

        log.debug("%d children found.", children.getItems().size());

        if (initDependencies) {
            initModels(children.getItems());
        }

        return children;
    }

    @Override
    protected void initModel(T model) {
        Validate.notNull(model);

        log.trace("Initializing lazy loaded dependencies of model %s.", model);

        model.getChildren().size();
    }

    protected abstract <E extends T> BaseHierarchicalRepository<E, ID> getRepository();
}
