package cz.kea.impl.repositories;

import java.io.Serializable;
import java.util.List;

import cz.kea.api.model.HierarchicalModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Base hierarchical repository.
 *
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
@NoRepositoryBean
public interface BaseHierarchicalRepository<T extends HierarchicalModel<ID>, ID extends Serializable> extends BaseRepository<T, ID> {

    List<T> findByParent(T parent);

    List<T> findByParent(T parent, Sort sort);

    Page<T> findByParent(T parent, Pageable pageable);

    List<T> findByParent(T parent, Specification<T> specification);

    Page<T> findByParent(T parent, Specification<T> specification, Pageable pageable);

    List<T> findByParent(T parent, Specification<T> specification, Sort sort);

    List<T> findByParentIsNull();

    List<T> findByParentIsNull(Sort sort);

    Page<T> findByParentIsNull(Pageable pageable);

    List<T> findByParentIsNull(Specification<T> specification);

    Page<T> findByParentIsNull(Specification<T> specification, Pageable pageable);

    List<T> findByParentIsNull(Specification<T> specification, Sort sort);

    long countByParent(T parent);

    long countByParent(T parent, Specification<T> specification);

    long countByParentIsNull();

    long countByParentIsNull(Specification<T> specification);
}
