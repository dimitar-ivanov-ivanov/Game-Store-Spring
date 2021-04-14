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
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "genres")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "genre_id")
    private long genreId;

    @NotBlank(message = TextConstants.NAME_CANNOT_BE_BLANK)
    private String name;

    @NotBlank(message = TextConstants.DESCRIPTION_CANNOT_BE_BLANK)
    @Column(name = "TEXT")
    private String description;

    @ManyToMany
    @JoinTable(
            name = "genres_games",
            joinColumns = @JoinColumn(
                    name = "genre_id",
                    referencedColumnName = "genre_id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "game_id",
                    referencedColumnName = "game_id"
            )
    )
    private Set<Game> games;

    public Genre(String name) {
        this.name = name;
        this.games = new HashSet<>();
    }
}
