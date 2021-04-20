package gamestore.repositories;

import gamestore.models.entities.security.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * The interface Authority repository.
 *
 * @author Dimitar Ivanov
 */
@Repository
public interface AuthorityRepository extends
        JpaRepository<Authority, Long> {

    /**
     * Gets authority by name.
     *
     * @param name the authority name
     * @return the authority by name
     */
    Optional<Authority> getByName(String name);
}
