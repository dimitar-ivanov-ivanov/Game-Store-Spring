package gamestore.models.entities.game;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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

    private String name;

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
