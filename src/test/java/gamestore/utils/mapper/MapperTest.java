package gamestore.utils.mapper;

import gamestore.models.bindings.UserRegisterBindingModel;
import gamestore.models.dtos.*;
import gamestore.models.entities.game.Game;
import gamestore.models.entities.user.*;
import gamestore.models.enums.BadgeLevel;
import gamestore.models.enums.Gender;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MapperTest {

    private ModelMapper mapper = modelMapper();

    private User user = new User(
            "Dimitar",
            "Ivanov",
            LocalDate.of(1999, 1, 2),
            "dI1mn",
            "dimitar.i.ivanov@abv.b",
            "ab_cdA56",
            Gender.MALE
    );

    private Game game = new Game(
            "Call of duty",
            BigDecimal.valueOf(250),
            "aaa",
            "aaa",
            LocalDate.of(2011, 1, 2),
            BigDecimal.valueOf(300),
            "Action game"
    );

    ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        ModelMapperConfig config = new ModelMapperConfig(modelMapper);
        return modelMapper;
    }

    @Test
    void shouldMapRegisterBindingToUser() throws IllegalAccessException {
        UserRegisterBindingModel bindingModel = new UserRegisterBindingModel(
                "dI1mn",
                "dimitar.i.ivanov@abv.b",
                "ab_cdA56",
                "Dimitar",
                "Ivanov",
                LocalDate.of(1999, 1, 2),
                Gender.MALE
        );

        User user = mapper.map(bindingModel, User.class);

        assertThat(user.getUsername()).isEqualTo(bindingModel.getUsername());
        assertThat(user.getEmail()).isEqualTo(bindingModel.getEmail());
        assertThat(user.getPassword()).isEqualTo(bindingModel.getPassword());
        assertThat(user.getFirstName()).isEqualTo(bindingModel.getFirstName());
        assertThat(user.getLastName()).isEqualTo(bindingModel.getLastName());
        assertThat(user.getBirthDate()).isEqualTo(bindingModel.getBirthDate());
    }

    @Test
    void shouldMapUserWishListGameToDto() {
        UserWishlistGame userWishlistGame = new UserWishlistGame(
                user, game, LocalDate.of(2021, 1, 1)
        );

        UserWishlistGameDto dto = mapper.map(userWishlistGame, UserWishlistGameDto.class);

        assertThat(user.getUsername()).isEqualTo(dto.getUsername());
        assertThat(game.getName()).isEqualTo(dto.getGameName());
        assertThat(userWishlistGame.getAddedOn()).isEqualTo(dto.getAddedOn());
    }

    @Test
    void shouldMapUserBoughtGameToDto() {
        UserBoughtGame userBoughtGame = new UserBoughtGame(
                user, game, LocalDate.of(2021, 1, 1), 200, 20
        );

        UserBoughtGameDto dto = mapper.map(userBoughtGame, UserBoughtGameDto.class);
        assertThat(user.getUsername()).isEqualTo(dto.getUsername());
        assertThat(game.getName()).isEqualTo(dto.getGameName());
        assertThat(userBoughtGame.getBoughtOn()).isEqualTo(dto.getBoughtOn());
        assertThat(userBoughtGame.getHoursPlayedTotal()).isEqualTo(dto.getHoursPlayedTotal());
        assertThat(userBoughtGame.getHoursPlayerLastTwoWeeks()).isEqualTo(dto.getHoursPlayerLastTwoWeeks());
    }

    @Test
    void shouldMapUserAchievmentToDto() {
        Achievement ach = new Achievement(
                "Kill 100 enemies.",
                game
        );

        UserAchievement userAchievement = new UserAchievement(
                user, ach, LocalDate.of(2021, 4, 4)
        );

        UserAchievementDto dto = mapper.map(userAchievement, UserAchievementDto.class);
        assertThat(user.getUsername()).isEqualTo(dto.getUsername());
        assertThat(ach.getName()).isEqualTo(dto.getAchievementName());
        assertThat(userAchievement.getEarnedOn()).isEqualTo(dto.getEarnedOn());
    }

    @Test
    void shouldMapUserGameBadgeToDto() {
        Badge badge = new Badge(BadgeLevel.Advanced);
        UserGameBadge userGameBadge = new UserGameBadge(
                badge, game, user, BigDecimal.valueOf(10), LocalDate.of(1999, 1, 1)
        );

        UserGameBadgeDto dto = mapper.map(userGameBadge, UserGameBadgeDto.class);

        assertThat(user.getUsername()).isEqualTo(dto.getUsername());
        assertThat(game.getName()).isEqualTo(dto.getGameName());
        assertThat(badge.getLevel().toString()).isEqualTo(dto.getBadgeName());
        assertThat(userGameBadge.getPrice()).isEqualTo(dto.getPrice());
        assertThat(userGameBadge.getEarnedOn()).isEqualTo(dto.getEarnedOn());
    }

}
