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
 * The type Publisher.
 *
 * @author Dimitar Ivanov
 */
@Getter
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "publishers")
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "publisher_id")
    private Long publisherId;

    /**
     * The name of the publisher
     * Must not be empty or null.
     *
     * @see TextConstants#NAME_CANNOT_BE_BLANK
     */
    @NotBlank(message = TextConstants.NAME_CANNOT_BE_BLANK)
    private String name;

    /**
     * The games published by this publisher
     *
     * @see Game
     */
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

    /**
     * Instantiates a new Publisher.
     *
     * @param name the name of the publisher
     */
    public Publisher(String name) {
        this.name = name;
        this.games = new HashSet<>();
    }
}
