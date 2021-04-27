package gamestore.utils.mapper;

import gamestore.models.dtos.*;
import gamestore.models.entities.user.*;
import gamestore.models.bindings.UserRegisterBindingModel;
import gamestore.utils.mapper.converters.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

/**
 * The Model mapper config used for explicitly creating mapping between models.
 *
 * @author Dimitar Ivanov
 */
@Component
public class ModelMapperConfig {

    private final ModelMapper mapper;

    /**
     * Instantiates a new Model mapper config.
     *
     * @param mapper the mapper
     */
    public ModelMapperConfig(ModelMapper mapper) {
        this.mapper = mapper;
        this.initialize();
    }

    private void initialize() {
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        registerRequestToUserMapping();
        userToUserGetDto();
        userAchievementToDto();
        userBoughtGamesToDto();
        userWishlistGamesToDto();
        userGameBadgesToDto();
        userFriendsToDto();
    }

    private void userToUserGetDto() {
        TypeMap<User, UserGetDto> map =
                this.mapper.getTypeMap(User.class, UserGetDto.class);

        if (map == null) {
            this.mapper.createTypeMap(User.class, UserGetDto.class)
                    .addMappings(mapper -> {
                        mapper.map(User::getUsername, UserGetDto::setUsername);
                        mapper.map(User::getEmail, UserGetDto::setEmail);
                        mapper.map(User::getFirstName, UserGetDto::setFirstName);
                        mapper.map(User::getLastName, UserGetDto::setLastName);
                        mapper.map(User::getBirthDate, UserGetDto::setBirthDate);
                        mapper.map(User::getGender, UserGetDto::setGender);
                    });
        }
    }

    /**
     * Create type map for parsing the user's friends
     */
    private void userFriendsToDto() {
        TypeMap<User, UserFriendDto> map =
                this.mapper.getTypeMap(User.class, UserFriendDto.class);
        if (map == null) {
            this.mapper.createTypeMap(User.class, UserFriendDto.class)
                    .addMappings(mapper -> mapper.using(new FriendConverter()));
        }
    }

    /**
     * Create type map for parsing UserWishlistGame to dto
     */
    private void userWishlistGamesToDto() {
        TypeMap<UserWishlistGame, UserWishlistGameDto> map =
                this.mapper.getTypeMap(UserWishlistGame.class, UserWishlistGameDto.class);
        if (map == null) {
            this.mapper.createTypeMap(UserWishlistGame.class, UserWishlistGameDto.class)
                    .addMappings(mapper -> mapper.using(new UserWishlistGameConverter()));
        }
    }

    /**
     * Create type map for parsing UserBoughtGame to dto
     */
    private void userBoughtGamesToDto() {
        TypeMap<UserBoughtGame, UserBoughtGameDto> map =
                this.mapper.getTypeMap(UserBoughtGame.class, UserBoughtGameDto.class);
        if (map == null) {
            this.mapper.createTypeMap(UserBoughtGame.class, UserBoughtGameDto.class)
                    .addMappings(mapper -> mapper.using(new UserBoughtGameConverter()));
        }
    }

    /**
     * Create type map for parsing UserGameBadge to dto
     */
    private void userGameBadgesToDto() {
        TypeMap<UserGameBadge, UserGameBadgeDto> map =
                this.mapper.getTypeMap(UserGameBadge.class, UserGameBadgeDto.class);
        if (map == null) {
            this.mapper.createTypeMap(UserGameBadge.class, UserGameBadgeDto.class)
                    .addMappings(mapper -> mapper.using(new UserGameBadgeConverter()));
        }
    }

    /**
     * Create type map for parsing UserAchievement to dto
     */
    private void userAchievementToDto() {
        TypeMap<UserAchievement, UserAchievementDto> map =
                this.mapper.getTypeMap(UserAchievement.class, UserAchievementDto.class);
        if (map == null) {
            this.mapper.createTypeMap(UserAchievement.class, UserAchievementDto.class)
                    .addMappings(mapper -> mapper.using(new UserAchievementConverter()));
        }
    }

    /**
     * Create typeMap for parsing a register binding model to User
     */
    private void registerRequestToUserMapping() {

        TypeMap<UserRegisterBindingModel, User> typeMap = this.mapper.getTypeMap(UserRegisterBindingModel.class, User.class);

        if (typeMap == null) {
            this.mapper.createTypeMap(UserRegisterBindingModel.class, User.class)
                    .addMappings(mapper -> {
                        mapper.map(UserRegisterBindingModel::getUsername, User::setUsername);
                        mapper.map(UserRegisterBindingModel::getEmail, User::setEmail);
                        mapper.map(UserRegisterBindingModel::getPassword, User::setPassword);
                        mapper.map(UserRegisterBindingModel::getFirstName, User::setFirstName);
                        mapper.map(UserRegisterBindingModel::getLastName, User::setLastName);
                        mapper.map(UserRegisterBindingModel::getBirthDate, User::setBirthDate);
                        mapper.map(UserRegisterBindingModel::getGender, User::setGender);
                    });
        }
    }
}
