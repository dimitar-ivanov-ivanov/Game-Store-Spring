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
 */
@AllArgsConstructor
@RestController
@RequestMapping(path = "/user")
public class UserController {

    /**
     * The service used for internal logic
     */
    private final UserService userService;

    /**
     * The mapper used to parse from user to dto
     */
    private final ModelMapper mapper;

    /**
     * Get user by id.
     * Only accessible by users who have the user:read authority
     *
     * @param userId the user id
     * @return the user
     */
    @GetMapping(path = "{userId}")
    @PreAuthorize("hasAuthority('user:read')")
    @ResponseStatus(HttpStatus.FOUND)
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
     */
    @PostMapping(path = "/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerNewUser(@Valid @RequestBody UserRegisterBindingModel register) {
        userService.registerUser(register);
    }

    /**
     * Delete user.
     * Only accessible by users who have the user:delete authority
     *
     * @param userId the user id
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
     */
    @PutMapping(path = "/update/{userId}")
    @PreAuthorize("hasAuthority('user:update')")
    public void updateUser(@PathVariable Long userId,
                           @RequestBody User updatedUser) {
        throw new NotYetImplementedException();
    }
}
