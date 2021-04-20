package gamestore.models.entities.user;

import gamestore.models.entities.game.Game;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * The type User bought game.
 *
 * @author Dimitar Ivanov
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "users_bought_games")
public class UserBoughtGame implements Serializable {

    @EmbeddedId
    private UserGameId id;

    /**
     * The user that bought the game.
     *
     * @see User
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * The game that was bough.
     *
     * @see Game
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("gameId")
    @JoinColumn(name = "game_id")
    private Game game;

    /**
     * When the game was bought.
     */
    @Column(name = "bought_on")
    private LocalDate boughtOn;

    /**
     * The total hours the user played.
     */
    @Column(name = "hours_played_total")
    private int hoursPlayedTotal;

    /**
     * The hours the user played in the last two weeks.
     */
    @Column(name = "hours_played_last_two_weeks")
    private int hoursPlayerLastTwoWeeks;
}
