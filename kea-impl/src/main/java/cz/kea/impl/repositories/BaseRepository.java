package cz.kea.impl.repositories;

import java.io.Serializable;

import cz.kea.api.model.Identifiable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
@NoRepositoryBean
public interface BaseRepository<T extends Identifiable<ID>, ID extends Serializable> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {
}
