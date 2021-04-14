package gamestore.models.entities.user;


import gamestore.utils.constants.TextConstants;
import gamestore.models.entities.game.Game;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

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

    @NotBlank(message = TextConstants.NAME_CANNOT_BE_BLANK)
    private String name;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    public Achievement(String name) {
        this.name = name;
    }
}
