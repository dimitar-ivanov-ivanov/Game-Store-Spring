package gamestore.models.entities.game;

import gamestore.constants.Messages;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "tags")
public class Tag implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private long tagId;

    @NotBlank(message = Messages.NAME_CANNOT_BE_BLANK)
    private String name;

    @NotBlank(message = Messages.DESCRIPTION_CANNOT_BE_BLANK)
    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToMany
    @JoinTable(
            name = "tags_games",
            joinColumns = @JoinColumn(
                    name = "tag_id",
                    referencedColumnName = "tag_id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "game_id",
                    referencedColumnName = "game_id"
            )
    )
    private Set<Game> games;

    public Tag(String name) {
        this.name = name;
        this.games = new HashSet<>();
    }
}
