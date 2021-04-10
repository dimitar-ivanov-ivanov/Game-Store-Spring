package gamestore.models.entities.game;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    private String name;
    private BigDecimal price;

    @Column(name = "trailer_url")
    private String trailerUrl;
    private String thumbnail;

    @Column(name = "release_date")
    private LocalDate releaseDate;
    private double size;

    @Column(columnDefinition = "text")
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

    @OneToMany(
            mappedBy = "game",
            orphanRemoval = true
    )
    private Set<Tag> tags;

    public Game(String name,
                BigDecimal price,
                String trailerUrl,
                String thumbnail,
                LocalDate releaseDate,
                double size,
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
    }
}
