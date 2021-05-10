package gamestore.models.entities.game;

import gamestore.utils.constants.TextConstants;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * The type Tag.
 *
 * @author Dimitar Ivanov
 */
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "tags")
public class Tag implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private long tagId;

    /**
     * The name of the tag.
     * Must not be empty or null.
     *
     * @see TextConstants#NAME_CANNOT_BE_BLANK
     */
    @NotBlank(message = TextConstants.NAME_CANNOT_BE_BLANK)
    private String name;

    /**
     * The description of the tag.
     * Must not be empty or null.
     *
     * @see TextConstants#DESCRIPTION_CANNOT_BE_BLANK
     */
    @NotBlank(message = TextConstants.DESCRIPTION_CANNOT_BE_BLANK)
    @Column(columnDefinition = "TEXT")
    private String description;

    /**
     * All games that have this tag
     *
     * @see Game
     */
    @ManyToMany
    @JoinTable(
            name = "tags_games",
            joinColumns = @JoinColumn(
                    name = "tag_id",
                    referencedColumnName = "tag_id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "game_id",
                    referencedColumnName = "game_id"
            )
    )
    private Set<Game> games;

    /**
     * Instantiates a new Tag.
     *
     * @param name the name of the tag
     */
    public Tag(String name) {
        this.name = name;
        this.games = new HashSet<>();
    }
}
