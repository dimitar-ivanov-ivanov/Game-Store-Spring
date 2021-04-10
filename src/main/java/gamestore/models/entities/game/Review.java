package gamestore.models.entities.game;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "reviews")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Review extends GameContent implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private long reviewId;
}
