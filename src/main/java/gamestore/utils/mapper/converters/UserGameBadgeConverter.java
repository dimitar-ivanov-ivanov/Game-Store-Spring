package gamestore.utils.mapper.converters;

import gamestore.models.dtos.UserBoughtGameDto;
import gamestore.models.dtos.UserGameBadgeDto;
import gamestore.models.entities.user.UserGameBadge;
import org.modelmapper.AbstractConverter;

/**
 * The User game badge converter.
 *
 * @author Dimitar Ivanov
 * @see UserGameBadge
 * @see UserGameBadgeDto
 */
public class UserGameBadgeConverter
        extends AbstractConverter<UserGameBadge, UserGameBadgeDto> {
    @Override
    protected UserGameBadgeDto convert(UserGameBadge userGameBadge) {
        return new UserGameBadgeDto(
                userGameBadge.getUser().getUsername(),
                userGameBadge.getGame().getName(),
                userGameBadge.getBadge().getLevel().toString(),
                userGameBadge.getEarnedOn(),
                userGameBadge.getPrice()
        );
    }
}
