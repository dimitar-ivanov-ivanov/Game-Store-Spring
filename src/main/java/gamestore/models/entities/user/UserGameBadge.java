package gamestore.models.entities.user;


import gamestore.utils.constants.TextConstants;
import gamestore.utils.constants.NumberConstants;
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

/**
 * The type User game badge.
 *
 * @author Dimitar Ivanov
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "user_badges_games")
public class UserGameBadge implements Serializable {

    @EmbeddedId
    private UserGameBadgeId id;

    /**
     * The badge that was earned.
     *
     * @see Badge
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("badgeId")
    @JoinColumn(name = "badge_id")
    private Badge badge;

    /**
     * The game for which a badge was earned.
     *
     * @see Game
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("gameId")
    @JoinColumn(name = "game_id")
    private Game game;

    /**
     * The user that earned the badge.
     *
     * @see User
     */
    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * The price of the badge.
     * Must be in range of PRICE_MIN TO PRICE_MAX
     *
     * @see NumberConstants#PRICE_MIN
     * @see NumberConstants#PRICE_MAX
     * @see TextConstants#PRICE_CANNOT_BE_SMALLER_OR_EQUAL_TO
     * @see TextConstants#PRICE_CANNOT_BE_BIGGER_THAN
     */
    @DecimalMin(
            value = NumberConstants.PRICE_MIN + "",
            inclusive = false,
            message = TextConstants.PRICE_CANNOT_BE_SMALLER_OR_EQUAL_TO + NumberConstants.PRICE_MIN
    )
    @DecimalMax(
            value = NumberConstants.PRICE_MAX + "",
            message = TextConstants.PRICE_CANNOT_BE_BIGGER_THAN + NumberConstants.PRICE_MAX
    )
    private BigDecimal price;

    /**
     * When the badge was earned.
     */
    @Column(name = "earned_on")
    private LocalDate earnedOn;

    public UserGameBadge(Badge badge, Game game, User user, BigDecimal price, LocalDate earnedOn) {
        this.id = new UserGameBadgeId(badge.getBadgeId(), game.getGameId(), user.getUserId());
        this.badge = badge;
        this.game = game;
        this.user = user;
        this.price = price;
        this.earnedOn = earnedOn;
    }
}
