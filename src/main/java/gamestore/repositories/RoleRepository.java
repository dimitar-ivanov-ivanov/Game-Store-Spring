package gamestore.repositories;

import gamestore.models.entities.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * The interface Role repository.
 *
 * @author Dimitar Ivanov
 */
@Repository
public interface RoleRepository extends
        JpaRepository<Role, Long> {

    /**
     * Gets role by name.
     *
     * @param name the role name
     * @return the role by name
     * @see Role
     */
    Optional<Role> getByName(String name);
}
