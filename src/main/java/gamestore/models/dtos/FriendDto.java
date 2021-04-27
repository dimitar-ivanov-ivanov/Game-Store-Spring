package gamestore.models.dtos;

import gamestore.models.entities.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * The Friend dto only containing the name for now.
 *
 * @author Dimitar Ivanov
 * @see User#getFriends()
 */
@AllArgsConstructor
@Getter
public class FriendDto implements Serializable {

    private Set<String> friends;

    public FriendDto() {
        this.friends = new HashSet<>();
    }
}
