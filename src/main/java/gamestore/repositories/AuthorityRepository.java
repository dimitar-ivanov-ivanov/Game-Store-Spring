package gamestore.repositories;

import gamestore.models.entities.security.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorityRepository extends
        JpaRepository<Authority, Long> {

    Optional<Authority> getByName(String name);
}
