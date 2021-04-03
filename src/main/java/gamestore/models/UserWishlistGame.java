package gamestore.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@EqualsAndHashCode
@Entity(name = "UserWishlistGame")
@Table(name = "users_wishlist_games")
public class UserWishlistGame implements Serializable {

    @EmbeddedId
    private UserGameId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("gameId")
    private Game game;

    @Column(name = "added_on")
    private LocalDate addedOn;

    public UserWishlistGame(User user,
                            Game game,
                            LocalDate addedOn) {
        this.id = new UserGameId(user.getUserId(), game.getGameId());
        this.user = user;
        this.game = game;
        this.addedOn = addedOn;
    }
}
