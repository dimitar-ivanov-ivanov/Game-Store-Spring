package gamestore.models.entities.game;

import gamestore.utils.constants.TextConstants;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "publishers")
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "publisher_id")
    private Long publisherId;

    @NotBlank(message = TextConstants.NAME_CANNOT_BE_BLANK)
    private String name;

    @ManyToMany
    @JoinTable(
            name = "publishers_games",
            joinColumns = @JoinColumn(
                    name = "publisher_id",
                    referencedColumnName = "publisher_id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "game_id",
                    referencedColumnName = "game_id"
            )
    )
    private Set<Game> games;

    public Publisher(String name) {
        this.name = name;
        this.games = new HashSet<>();
    }
}
