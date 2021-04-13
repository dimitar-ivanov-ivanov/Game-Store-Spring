package gamestore.models.entities.game;

import gamestore.constants.Messages;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@MappedSuperclass
public class GameContent {

    @NotBlank(message = Messages.DESCRIPTION_CANNOT_BE_BLANK)
    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;
}
