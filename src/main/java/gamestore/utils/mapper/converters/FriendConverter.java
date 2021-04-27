package gamestore.utils.mapper.converters;

import gamestore.models.dtos.UserFriendDto;
import gamestore.models.entities.user.User;
import org.modelmapper.AbstractConverter;

import java.util.stream.Collectors;

/**
 * The Friend converter.
 *
 * @author Dimitar Ivanov
 * @see UserFriendDto
 * @see User#getFriends()
 */
public class FriendConverter extends AbstractConverter<User, UserFriendDto> {
    @Override
    protected UserFriendDto convert(User user) {
        return new UserFriendDto(user.getUsername());
    }
}