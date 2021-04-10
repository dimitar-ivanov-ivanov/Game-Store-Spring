package gamestore.models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
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
}
