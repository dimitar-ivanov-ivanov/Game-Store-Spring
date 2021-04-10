package gamestore.models.entities.game;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "comments")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Comment extends GameContent implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private long commentId;
}
