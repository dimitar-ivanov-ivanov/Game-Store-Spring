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
 */
@Getter
@NoArgsConstructor
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

    @NotBlank(message = TextConstants.NAME_CANNOT_BE_BLANK)
    private String name;

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

    @DecimalMin(
            value = NumberConstants.SIZE_MIN + "",
            message = TextConstants.SIZE_CANNOT_BE_SMALLER_THAN + NumberConstants.SIZE_MIN
    )
    @DecimalMax(
            value = NumberConstants.SIZE_MAX + "",
            message = TextConstants.SIZE_CANNOT_BE_BIGGER_THAN + NumberConstants.SIZE_MAX
    )
    private BigDecimal size;

    @NotBlank(message = TextConstants.DESCRIPTION_CANNOT_BE_BLANK)
    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToMany(
            mappedBy = "game",
            orphanRemoval = true
    )
    private Set<Achievement> achievements;

    @OneToMany(
            mappedBy = "game",
            orphanRemoval = true
    )
    private Set<Review> reviews;

    @ManyToMany(mappedBy = "games")
    private Set<Tag> tags;

    @ManyToMany(mappedBy = "games")
    private Set<Publisher> publishers;

    @ManyToMany(mappedBy = "games")
    private Set<Genre> genres;

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
}
