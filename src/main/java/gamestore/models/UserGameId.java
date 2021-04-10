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
@EqualsAndHashCode
public class UserGameId implements Serializable {

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "game_id")
    private Long gameId;

    public UserGameId(Long userId, Long gameId) {
        this.userId = userId;
        this.gameId = gameId;
    }
}
