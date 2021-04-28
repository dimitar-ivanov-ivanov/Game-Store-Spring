package gamestore.controllers;

import gamestore.models.dtos.UserGetDto;
import gamestore.models.entities.user.User;
import gamestore.services.UserService;
import lombok.AllArgsConstructor;
import org.hibernate.cfg.NotYetImplementedException;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The User management controller.
 * Only for actions affecting all users
 *
 * @author Dimitar Ivanov
 */
@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserManagementController {

    /**
     * The service used for internal logic
     *
     * @see UserService
     */
    private final UserService userService;


    /**
     * The mapper used to parse from user to dto
     *
     * @see gamestore.utils.mapper.ModelMapperConfig
     */
    private final ModelMapper mapper;

    /**
     * Gets all users.
     *
     * @return the all users
     * @see gamestore.models.entities.security.Authority
     * @see gamestore.utils.seed.SetupDataLoader -> to find out which initial user have the roles
     * @see UserService#getAllUsers()
     */
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','ADMIN_TRAINEE')")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Collection<UserGetDto> getAllUsers() {
        List<User> users = userService.getAllUsers();
        List<UserGetDto> dtos = users
                .stream()
                .map(user -> mapper.map(user, UserGetDto.class))
                .collect(Collectors.toList());

        return dtos;
    }

    // TODO: 4/9/2021 Find all users by email,name,find their roles,their games


}
