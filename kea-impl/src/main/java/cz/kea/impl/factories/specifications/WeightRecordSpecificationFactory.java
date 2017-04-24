package cz.kea.impl.factories.specifications;

import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import cz.kea.api.utils.Filter;
import cz.kea.impl.entities.WeightRecordEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
@Component
public class WeightRecordSpecificationFactory implements SpecificationFactory<WeightRecordEntity, Long> {

    @Override
    public Specification<WeightRecordEntity> createSpecification(Map<Filter, Object> filter) {
        return new Specification<WeightRecordEntity>() {
            @Override
            public Predicate toPredicate(Root<WeightRecordEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return null;
            }
        };
    }
}
