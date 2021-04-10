package gamestore.models;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "achievements")
public class Achievement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "achievement_id")
    private Long achievementId;

    private String name;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    public Achievement(String name) {
        this.name = name;
    }
}
