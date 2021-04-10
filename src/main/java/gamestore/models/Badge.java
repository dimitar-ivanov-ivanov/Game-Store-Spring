package gamestore.models;


import gamestore.models.enums.BadgeLevel;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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
