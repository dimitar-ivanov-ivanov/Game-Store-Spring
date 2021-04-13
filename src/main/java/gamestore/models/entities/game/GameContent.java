package gamestore.models.entities.game;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@MappedSuperclass
public class GameContent {

    @NotBlank(message = "description cannot be blank")
    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;
}
