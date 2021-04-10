package gamestore.models.entities.game;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "tags")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Tag extends GameContent implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private long tagId;

    private String name;

    public Tag(String name) {
        this.name = name;
    }
}
