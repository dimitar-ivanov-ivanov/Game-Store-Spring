package gamestore.models.entities.user;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * The type User achievement.
 *
 * @author Dimitar Ivanov
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "user_achievements")
public class UserAchievement {

    @EmbeddedId
    private UserAchievementId id;

    /**
     * The user that achieved the achievement
     *
     * @see User
     */
    @MapsId("userId")
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * The achievement
     *
     * @see Achievement
     */
    @MapsId("achievementId")
    @ManyToOne
    @JoinColumn(name = "achievement_id")
    private Achievement achievement;

    /**
     * What date was the achievement earned.
     */
    @Column(name = "earned_on")
    private LocalDate earnedOn;

    public UserAchievement(User user, Achievement achievement, LocalDate earnedOn) {
        this.id = new UserAchievementId(user.getUserId(), achievement.getAchievementId());
        this.user = user;
        this.achievement = achievement;
        this.earnedOn = earnedOn;
    }
}
