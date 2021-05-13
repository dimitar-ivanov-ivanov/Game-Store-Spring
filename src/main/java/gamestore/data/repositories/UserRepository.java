package gamestore.data.repositories;

import gamestore.models.entities.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * The interface User repository.
 *
 * @author Dimitar Ivanov
 */
@Repository
public interface UserRepository extends
        JpaRepository<User, Long> {

    /**
     * Gets user by username.
     *
     * @param username the user's username
     * @return the user by username
     * @see User
     */
    @Async("asyncExecutor")
    CompletableFuture<Optional<User>> getByUsername(String username);


}
