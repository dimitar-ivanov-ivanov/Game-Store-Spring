package gamestore.models;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UserGameBadgeId implements Serializable {

    @Column(name = "badge_id")
    private Long badgeId;

    @Column(name = "game_id")
    private Long gameId;

    @Column(name = "user_id")
    private Long userId;
}
