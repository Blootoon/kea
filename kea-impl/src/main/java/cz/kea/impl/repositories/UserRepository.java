package cz.kea.impl.repositories;

import cz.kea.impl.entities.UserEntity;
import org.springframework.stereotype.Repository;

/**
 * @author Jakub Jaros (jakub.jaros@ibacz.eu)
 */
@Repository
public interface UserRepository extends BaseRepository<UserEntity, Long> {

    UserEntity findByEmail(String email);
}
