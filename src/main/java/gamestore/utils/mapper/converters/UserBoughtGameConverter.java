package gamestore.utils.mapper.converters;

import gamestore.models.dtos.UserBoughtGameDto;
import gamestore.models.entities.user.UserBoughtGame;
import org.modelmapper.AbstractConverter;

/**
 * The User bought game converter.
 *
 * @author Dimitar Ivanov
 * @see UserBoughtGame
 * @see UserBoughtGameDto
 */
public class UserBoughtGameConverter
        extends AbstractConverter<UserBoughtGame, UserBoughtGameDto> {
    @Override
    protected UserBoughtGameDto convert(UserBoughtGame userBoughtGame) {
        return new UserBoughtGameDto(
                userBoughtGame.getUser().getUsername(),
                userBoughtGame.getGame().getName(),
                userBoughtGame.getBoughtOn(),
                userBoughtGame.getHoursPlayedTotal(),
                userBoughtGame.getHoursPlayerLastTwoWeeks()
        );
    }
}

