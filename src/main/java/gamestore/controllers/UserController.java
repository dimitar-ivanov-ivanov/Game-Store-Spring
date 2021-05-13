package gamestore.controllers;

import gamestore.models.bindings.UserRegisterBindingModel;
import gamestore.models.dtos.UserGetDto;
import gamestore.models.entities.user.User;
import gamestore.data.services.UserService;
import lombok.AllArgsConstructor;
import org.hibernate.cfg.NotYetImplementedException;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * The User controller.
 * Only for actions affecting a single user
 *
 * @author Dimitar Ivanov
 */
@EnableAsync
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
    @GetMapping(produces = "application/json")
    @PreAuthorize("hasAuthority('user:read')")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public UserGetDto getUser(@RequestParam(name = "userId") Long userId) throws ExecutionException, InterruptedException {
        CompletableFuture<User> futureUser = userService.getById(userId);
        UserGetDto dto = mapper.map(futureUser.get(), UserGetDto.class);
        return dto;
    }

    /**
     * Register new user.
     *
     * @param register the details of the incoming user to be registered
     * @see UserRegisterBindingModel
     * @see UserService#registerUser(UserRegisterBindingModel)
     */
    @PostMapping(path = "/register",
            consumes = "application/json",
            produces = "application/json"
    )
    @ResponseStatus(HttpStatus.CREATED)
    public UserGetDto registerNewUser(@Valid @RequestBody UserRegisterBindingModel register) throws ExecutionException, InterruptedException {
        User user = userService.registerUser(register);
        UserGetDto dto = mapper.map(user, UserGetDto.class);
        return dto;
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
        //change the request body to binding model
        throw new NotYetImplementedException();
    }
}
