package cz.kea.impl.factories.specifications;

import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import cz.kea.api.utils.Filter;
import cz.kea.impl.entities.BirdEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
@Component
public class BirdSpecificationFactory implements SpecificationFactory<BirdEntity, Long> {

    @Override
    public Specification<BirdEntity> createSpecification(Map<Filter, Object> filter) {
        return new Specification<BirdEntity>() {
            @Override
            public Predicate toPredicate(Root<BirdEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return null;
            }
        };
    }
}
