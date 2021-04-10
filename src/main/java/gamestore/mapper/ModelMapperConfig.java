package gamestore.mapper;

import gamestore.models.entities.user.User;
import gamestore.models.bindings.UserRegisterBindingModel;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

@Component
public class ModelMapperConfig {

    private final ModelMapper mapper;

    public ModelMapperConfig(ModelMapper mapper) {
        this.mapper = mapper;
        this.initialize();
    }

    private void initialize() {
        registerRequestToUserMapping();
    }

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
                        mapper.map(UserRegisterBindingModel::getDate, User::setBirthDate);
                    });
        }
    }
}
