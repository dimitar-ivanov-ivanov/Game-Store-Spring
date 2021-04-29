package gamestore.models.entities.user;

import gamestore.models.entities.game.Game;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * The type User wishlist game.
 *
 * @author Dimitar Ivanov
 */
@Getter
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "users_wishlist_games")
public class UserWishlistGame implements Serializable {

    @EmbeddedId
    private UserGameId id;

    /**
     * The user which added a game to his wishlist.
     *
     * @see User
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * The game which was added to a user's wishlist.
     *
     * @see Game
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("gameId")
    @JoinColumn(name = "game_id")
    private Game game;

    /**
     * When the game was added to a user's wishlist.
     */
    @Column(name = "added_on")
    private LocalDate addedOn;

    public UserWishlistGame(User user, Game game, LocalDate addedOn) {
        id = new UserGameId(user.getUserId(), game.getGameId());
        this.user = user;
        this.game = game;
        this.addedOn = addedOn;
    }
}
