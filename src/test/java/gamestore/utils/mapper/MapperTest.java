package gamestore.utils.mapper;

import gamestore.models.bindings.UserRegisterBindingModel;
import gamestore.models.dtos.UserGetDto;
import gamestore.models.dtos.UserWishlistGameDto;
import gamestore.models.entities.game.Game;
import gamestore.models.entities.user.User;
import gamestore.models.entities.user.UserWishlistGame;
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

    private UserRegisterBindingModel bindingModel = new UserRegisterBindingModel(
            "dI1mn",
            "dimitar.i.ivanov@abv.b",
            "ab_cdA56",
            "Dimitar",
            "Ivanov",
            LocalDate.of(1999, 1, 2),
            Gender.MALE
    );

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

}
