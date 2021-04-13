package gamestore.models.entities.game;

import gamestore.models.entities.user.Achievement;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import gamestore.constants.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

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

    @NotBlank(message = Messages.NAME_CANNOT_BE_BLANK)
    private String name;

    @DecimalMin(
            value = Numbers.PRICE_MIN + "",
            inclusive = false,
            message = Messages.PRICE_CANNOT_BE_SMALLER_OR_EQUAL_TO + Numbers.PRICE_MIN
    )
    @DecimalMax(
            value = Numbers.PRICE_MAX + "",
            message = Messages.PRICE_CANNOT_BE_BIGGER_THAN + Numbers.PRICE_MAX
    )
    private BigDecimal price;

    @Column(name = "trailer_url")
    private String trailerUrl;
    private String thumbnail;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @DecimalMin(
            value = Numbers.SIZE_MIN + "",
            message = Messages.SIZE_CANNOT_BE_SMALLER_THAN + Numbers.SIZE_MIN
    )
    @DecimalMax(
            value = Numbers.SIZE_MAX + "",
            message = Messages.SIZE_CANNOT_BE_BIGGER_THAN + Numbers.SIZE_MAX
    )
    private BigDecimal size;

    @NotBlank(message = Messages.DESCRIPTION_CANNOT_BE_BLANK)
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

    @OneToMany(
            mappedBy = "game",
            orphanRemoval = true
    )
    private Set<Comment> comments;

    @ManyToMany(mappedBy = "games")
    private Set<Tag> tags;

    @ManyToMany(mappedBy = "games")
    private Set<Publisher> publishers;

    @ManyToMany(mappedBy = "games")
    private Set<Genre> genres;

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
        this.comments = new HashSet<>();
        this.tags = new HashSet<>();
        this.publishers = new HashSet<>();
        this.genres = new HashSet<>();
    }
}
