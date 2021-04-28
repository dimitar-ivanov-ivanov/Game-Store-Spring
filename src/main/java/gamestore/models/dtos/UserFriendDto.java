package gamestore.models.dtos;

import gamestore.models.entities.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * The Friend dto only containing the name for now.
 *
 * @author Dimitar Ivanov
 * @see User#getFriends()
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserFriendDto implements Serializable {

    private String username;

}
