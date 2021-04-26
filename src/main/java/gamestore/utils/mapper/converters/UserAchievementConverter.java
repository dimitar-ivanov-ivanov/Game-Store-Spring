package gamestore.utils.mapper.converters;

import gamestore.models.dtos.UserAchievementDto;
import gamestore.models.entities.user.UserAchievement;
import org.modelmapper.AbstractConverter;

/**
 * The User achievement converter.
 *
 * @author Dimitar Ivanov
 * @see UserAchievement
 * @see UserAchievementDto
 */
public class UserAchievementConverter
        extends AbstractConverter<UserAchievement, UserAchievementDto> {
    @Override
    protected UserAchievementDto convert
            (UserAchievement userAchievement) {
        return new UserAchievementDto(
                userAchievement.getUser().getUsername(),
                userAchievement.getAchievement().getName(),
                userAchievement.getEarnedOn()
        );
    }
}
