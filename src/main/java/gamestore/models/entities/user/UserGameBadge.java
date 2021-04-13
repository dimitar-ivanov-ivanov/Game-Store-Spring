package gamestore.models.entities.user;


import gamestore.models.entities.game.Game;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "user_badges_games")
public class UserGameBadge implements Serializable {

    @EmbeddedId
    private UserGameBadgeId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("badgeId")
    @JoinColumn(name = "badge_id")
    private Badge badge;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("gameId")
    @JoinColumn(name = "game_id")
    private Game game;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @DecimalMin(value = "0", inclusive = false, message = "price cannot be smaller or equal to 0")
    @DecimalMax(value = "1000", message = "price cannot be bigger than 1000")
    private BigDecimal price;

    @Column(name = "earned_on")
    private LocalDate earnedOn;
}
