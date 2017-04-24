package cz.kea.impl.factories.specifications;

import java.io.Serializable;
import java.util.Map;

import cz.kea.api.model.Identifiable;
import cz.kea.api.utils.Filter;
import org.springframework.data.jpa.domain.Specification;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
public interface SpecificationFactory<T extends Identifiable<ID>, ID extends Serializable> {

    Specification<T> createSpecification(Map<Filter, Object> filter);
}
