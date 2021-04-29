package gamestore.models.entities.user;


import gamestore.utils.constants.TextConstants;
import gamestore.models.entities.game.Game;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

/**
 * The type Achievement.
 *
 * @author Dimitar Ivanov
 */
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "achievements")
public class Achievement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "achievement_id")
    private Long achievementId;

    /**
     * The name of the achievement.
     * Must be not be empty or null.
     *
     * @see TextConstants#NAME_CANNOT_BE_BLANK
     */
    @NotBlank(message = TextConstants.NAME_CANNOT_BE_BLANK)
    private String name;

    /**
     * The game that includes this achievement
     *
     * @see Game
     */
    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    /**
     * Instantiates a new Achievement.
     *
     * @param name the name
     */
    public Achievement(String name, Game game) {
        this.name = name;
        this.game = game;
    }
}
