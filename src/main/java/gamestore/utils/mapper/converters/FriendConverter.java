package gamestore.utils.mapper.converters;

import gamestore.models.dtos.FriendDto;
import gamestore.models.entities.user.User;
import org.modelmapper.AbstractConverter;

import java.util.stream.Collectors;

/**
 * The Friend converter.
 *
 * @author Dimitar Ivanov
 * @see FriendDto
 * @see User#getFriends()
 */
public class FriendConverter extends AbstractConverter<User, FriendDto> {
    @Override
    protected FriendDto convert(User user) {
        return new FriendDto(user
                .getFriends()
                .stream()
                .map(friend -> friend.getUsername())
                .collect(Collectors.toSet()));
    }
}
