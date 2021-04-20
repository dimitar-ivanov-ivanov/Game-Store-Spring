package gamestore.models.entities.user;


import gamestore.models.enums.BadgeLevel;
import lombok.*;

import javax.persistence.*;


/**
 * The type Badge.
 *
 * @author Dimitar Ivanov
 */
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

    /**
     * The level of the badge depends ot the hrs played.
     *
     * @see BadgeLevel
     */
    @Enumerated(EnumType.STRING)
    private BadgeLevel level;

    /**
     * Instantiates a new Badge.
     *
     * @param level the level
     */
    public Badge(BadgeLevel level) {
        this.level = level;
    }
}
