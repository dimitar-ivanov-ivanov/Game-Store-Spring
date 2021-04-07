package gamestore.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

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


}
