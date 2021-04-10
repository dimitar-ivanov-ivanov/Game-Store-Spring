package gamestore.models.entities.game;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "genres")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Genre extends GameContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "genre_id")
    private long genreId;

    private String name;

    public Genre(String name) {
        this.name = name;
    }
}
