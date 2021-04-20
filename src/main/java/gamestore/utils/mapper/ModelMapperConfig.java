package gamestore.utils.mapper;

import gamestore.models.dtos.UserGetDto;
import gamestore.models.entities.user.User;
import gamestore.models.bindings.UserRegisterBindingModel;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

/**
 * The Model mapper config used for explicitly creating mapping between models.
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
        registerRequestToUserMapping();
    }

    /**
     * Create typeMap for parsing a register binding model to User
     */
    private void registerRequestToUserMapping() {

        TypeMap<UserRegisterBindingModel, User> typeMap = this.mapper.getTypeMap(UserRegisterBindingModel.class, User.class);


        if (typeMap != null) {
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
