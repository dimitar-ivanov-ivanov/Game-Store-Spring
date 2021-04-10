package gamestore.models.entities.game;


import gamestore.models.enums.BadgeLevel;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Entity(name = "badge")
@Table(name = "badges")
public class Badge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "badge_id")
    private Long badgeId;

    @Enumerated(EnumType.STRING)
    private BadgeLevel level;

    public Badge(BadgeLevel level) {
        this.level = level;
    }
}
