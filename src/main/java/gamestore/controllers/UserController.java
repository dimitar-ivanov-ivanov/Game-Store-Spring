package gamestore.controllers;

import gamestore.models.bindings.UserRegisterBindingModel;
import gamestore.models.dtos.UserGetDto;
import gamestore.models.entities.user.User;
import gamestore.services.UserService;
import lombok.AllArgsConstructor;
import org.hibernate.cfg.NotYetImplementedException;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * The User controller.
 * Only for actions affecting a single user
 *
 * @author Dimitar Ivanov
 */
@AllArgsConstructor
@RestController
@RequestMapping(path = "/user")
public class UserController {

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
     * Get user by id.
     * Only accessible by users who have the user:read authority
     *
     * @param userId the user id
     * @return the found user
     * @see gamestore.models.entities.security.Authority
     * @see gamestore.utils.seed.SetupDataLoader -> to find out which initial have the authority
     * @see UserService#getById(Long)
     * @see UserGetDto
     */
    @GetMapping(path = "{userId}")
    @PreAuthorize("hasAuthority('user:read')")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public UserGetDto getUser(@PathVariable("userId") Long userId) {
        User user = userService.getById(userId);
        UserGetDto dto = mapper.map(user, UserGetDto.class);
        return dto;
    }

    /**
     * Register new user.
     *
     * @param register the details of the incoming user to be registered
     * @see UserRegisterBindingModel
     * @see UserService#registerUser(UserRegisterBindingModel)
     */
    @PostMapping(path = "/register")
    @PreAuthorize("hasAuthority('user:create')")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerNewUser(@Valid @RequestBody UserRegisterBindingModel register) {
        userService.registerUser(register);
    }

    /**
     * Delete user.
     * Only accessible by users who have the user:delete authority
     *
     * @param userId the user id
     * @see gamestore.models.entities.security.Authority
     * @see gamestore.utils.seed.SetupDataLoader -> to find out which initial have the authority
     */
    @DeleteMapping(path = "/delete/{userId}")
    @PreAuthorize("hasAuthority('user:delete')")
    public void deleteUser(@PathVariable Long userId) {
        throw new NotYetImplementedException();
    }

    /**
     * Update user.
     * Only accessible by users who have the user:update authority
     *
     * @param userId      the user id
     * @param updatedUser the updated user details
     * @see gamestore.models.entities.security.Authority
     * @see gamestore.utils.seed.SetupDataLoader -> to find out which initial have the authority
     */
    @PutMapping(path = "/update/{userId}")
    @PreAuthorize("hasAuthority('user:update')")
    public void updateUser(@PathVariable Long userId,
                           @RequestBody User updatedUser) {
        //change the request body to binding model -> register binding model?
        throw new NotYetImplementedException();
    }
}
