package cz.kea.impl.factories.specifications;

import java.util.Map;

import cz.kea.api.utils.Filter;
import cz.kea.impl.entities.UserEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
@Component
public class UserSpecificationFactory implements SpecificationFactory<UserEntity, Long> {

    @Override
    public Specification<UserEntity> createSpecification(Map<Filter, Object> filter) {
        return null;
    }
}
