package gamestore.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@EqualsAndHashCode
@Entity(name = "UserBoughtGame")
@Table(name = "users_bought_games")
public class UserBoughtGame implements Serializable {

    @EmbeddedId
    private UserGameId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("gameId")
    @JoinColumn(name = "game_id")
    private Game game;

    @Column(name = "bought_on")
    private LocalDate boughtOn;

    @Column(name = "hours_played_total")
    private int hoursPlayedTotal;

    @Column(name = "hours_played_last_two_weeks")
    private int hoursPlayerLastTwoWeeks;

    public UserBoughtGame(User user,
                          Game game,
                          LocalDate boughtOn,
                          int hoursPlayedTotal,
                          int hoursPlayerLastTwoWeeks) {
        this.id = new UserGameId(user.getUserId(), game.getGameId());
        this.user = user;
        this.game = game;
        this.boughtOn = boughtOn;
        this.hoursPlayedTotal = hoursPlayedTotal;
        this.hoursPlayerLastTwoWeeks = hoursPlayerLastTwoWeeks;
    }
}
