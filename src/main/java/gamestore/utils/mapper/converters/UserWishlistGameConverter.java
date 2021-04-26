package gamestore.utils.mapper.converters;

import gamestore.models.dtos.UserWishlistGameDto;
import gamestore.models.entities.user.UserWishlistGame;
import org.modelmapper.AbstractConverter;

/**
 * The User wishlist game converter.
 *
 * @author Dimitar Ivanov
 * @see UserWishlistGame
 * @see UserWishlistGameDto
 */
public class UserWishlistGameConverter extends
        AbstractConverter<UserWishlistGame, UserWishlistGameDto> {
    @Override
    protected UserWishlistGameDto convert(UserWishlistGame userWishlistGame) {
        return new UserWishlistGameDto(
                userWishlistGame.getUser().getUsername(),
                userWishlistGame.getGame().getName(),
                userWishlistGame.getAddedOn()
        );
    }
}
