package gamestore.models.entities.game;

import gamestore.models.entities.user.Achievement;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import gamestore.utils.constants.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * The type Game.
 *
 * @author Dimitar Ivanov
 */
@EqualsAndHashCode
@Entity(name = "Game")
@Table(name = "games")
public class Game implements Serializable {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(name = "game_id")
    private Long gameId;

    /**
     * The name of the game.
     * Must not be empty or null.
     *
     * @see TextConstants#NAME_CANNOT_BE_BLANK
     */
    @NotBlank(message = TextConstants.NAME_CANNOT_BE_BLANK)
    private String name;

    /**
     * The price of the game.
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

    @Column(name = "trailer_url")
    private String trailerUrl;

    private String thumbnail;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    /**
     * The size of the game.
     * Must be in range of SIZE_MIN TO SIZE_MAX
     *
     * @see NumberConstants#SIZE_MIN
     * @see NumberConstants#SIZE_MAX
     * @see TextConstants#SIZE_CANNOT_BE_SMALLER_THAN
     * @see TextConstants#SIZE_CANNOT_BE_BIGGER_THAN
     */
    @DecimalMin(
            value = NumberConstants.SIZE_MIN + "",
            message = TextConstants.SIZE_CANNOT_BE_SMALLER_THAN + NumberConstants.SIZE_MIN
    )
    @DecimalMax(
            value = NumberConstants.SIZE_MAX + "",
            message = TextConstants.SIZE_CANNOT_BE_BIGGER_THAN + NumberConstants.SIZE_MAX
    )
    private BigDecimal size;

    /**
     * The description of the game.
     * Must not be empty or null.
     *
     * @see TextConstants#DESCRIPTION_CANNOT_BE_BLANK
     */
    @NotBlank(message = TextConstants.DESCRIPTION_CANNOT_BE_BLANK)
    @Column(columnDefinition = "TEXT")
    private String description;

    /**
     * Achievements of the game.
     *
     * @see Achievement
     */
    @OneToMany(
            mappedBy = "game",
            orphanRemoval = true
    )
    private Set<Achievement> achievements;

    /**
     * Reviews written about the game
     *
     * @see Review
     */
    @OneToMany(
            mappedBy = "game",
            orphanRemoval = true
    )
    private Set<Review> reviews;

    /**
     * Tags that help categorize the game
     *
     * @see Tag
     */
    @ManyToMany(mappedBy = "games")
    private Set<Tag> tags;

    /**
     * The company/ies who published the game
     *
     * @see Publisher
     */
    @ManyToMany(mappedBy = "games")
    private Set<Publisher> publishers;

    /**
     * The genre/s of the game
     *
     * @see Genre
     */
    @ManyToMany(mappedBy = "games")
    private Set<Genre> genres;

    /**
     * Instantiates a new Game.
     */
    public Game() {
        this.achievements = new HashSet<>();
        this.reviews = new HashSet<>();
        this.tags = new HashSet<>();
        this.publishers = new HashSet<>();
        this.genres = new HashSet<>();
    }

    /**
     * Instantiates a new Game.
     *
     * @param name        the name
     * @param price       the price
     * @param trailerUrl  the trailer url
     * @param thumbnail   the thumbnail
     * @param releaseDate the release date
     * @param size        the size
     * @param description the description
     */
    public Game(String name,
                BigDecimal price,
                String trailerUrl,
                String thumbnail,
                LocalDate releaseDate,
                BigDecimal size,
                String description) {
        this.name = name;
        this.price = price;
        this.trailerUrl = trailerUrl;
        this.thumbnail = thumbnail;
        this.releaseDate = releaseDate;
        this.size = size;
        this.description = description;
        this.achievements = new HashSet<>();
        this.reviews = new HashSet<>();
        this.tags = new HashSet<>();
        this.publishers = new HashSet<>();
        this.genres = new HashSet<>();
    }

    public Long getGameId() {
        return gameId;
    }

    public String getName() {
        return name;
    }
}
