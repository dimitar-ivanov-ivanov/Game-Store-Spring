package gamestore.models.entities.game;

import gamestore.models.entities.user.Achievement;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @NotBlank(message = "name cannot be blank")
    private String name;

    @DecimalMin(value = "0", inclusive = false, message = "price cannot be smaller or equal to 0")
    @DecimalMax(value = "1000", message = "price cannot be bigger than 1000")
    private BigDecimal price;

    @Column(name = "trailer_url")
    private String trailerUrl;
    private String thumbnail;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @DecimalMin(value = "0.1", message = "size cannot be smaller than 0.1")
    @DecimalMax(value = "300", message = "size cannot be bigger than 300")
    private BigDecimal size;

    @NotBlank(message = "description cannot be blank")
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
