package gamestore.models.entities.game;

import gamestore.utils.constants.TextConstants;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

/**
 * The type Genre.
 *
 * @author Dimitar Ivanov
 */
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

    /**
     * The name of the genre.
     * Must not be empty or null.
     *
     * @see TextConstants#NAME_CANNOT_BE_BLANK
     */
    @NotBlank(message = TextConstants.NAME_CANNOT_BE_BLANK)
    private String name;

    /**
     * The description of the genre.
     * Must not be empty or null.
     *
     * @see TextConstants#DESCRIPTION_CANNOT_BE_BLANK
     */
    @NotBlank(message = TextConstants.DESCRIPTION_CANNOT_BE_BLANK)
    @Column(name = "TEXT")
    private String description;

    /**
     * The games that are in this genre
     *
     * @see Game
     */
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

    /**
     * Instantiates a new Genre.
     *
     * @param name the name
     */
    public Genre(String name) {
        this.name = name;
        this.games = new HashSet<>();
    }
}
