package gamestore.models.entities.user;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * The type User game id.
 *
 * @author Dimitar Ivanov
 */
@Embeddable
@NoArgsConstructor
@EqualsAndHashCode
public class UserGameId implements Serializable {

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "game_id")
    private Long gameId;

    /**
     * Instantiates a new User game id.
     *
     * @param userId the user id
     * @param gameId the game id
     */
    public UserGameId(Long userId, Long gameId) {
        this.userId = userId;
        this.gameId = gameId;
    }
}
