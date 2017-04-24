package cz.kea.impl.factories.specifications;

import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import cz.kea.api.utils.Filter;
import cz.kea.impl.entities.ContactEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
@Component
public class ContactSpecificationFactory implements SpecificationFactory<ContactEntity, Long> {

    @Override
    public Specification<ContactEntity> createSpecification(Map<Filter, Object> filter) {
        return new Specification<ContactEntity>() {
            @Override
            public Predicate toPredicate(Root<ContactEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return null;
            }
        };
    }
}
