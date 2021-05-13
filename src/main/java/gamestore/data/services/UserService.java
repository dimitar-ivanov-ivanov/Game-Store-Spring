package gamestore.data.services;

import gamestore.utils.exceptions.user.UsernameNotFoundException;
import gamestore.utils.constants.TextConstants;
import gamestore.utils.exceptions.user.UserNotFoundException;
import gamestore.models.entities.security.Role;
import gamestore.models.entities.user.User;
import gamestore.models.bindings.UserRegisterBindingModel;
import gamestore.data.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * The type User service.
 *
 * @author Dimitar Ivanov
 */
@EnableAsync
@AllArgsConstructor
@Service
@Transactional
public class UserService implements UserDetailsService {

    /**
     * The role service includes logic for roles.
     *
     * @see RoleService
     */
    private final RoleService roleService;

    /**
     * User repository for connecting with the db.
     *
     * @see UserRepository
     */
    private final UserRepository userRepository;

    /**
     * The password encoder.
     *
     * @see BCryptPasswordEncoder
     */
    private final BCryptPasswordEncoder passwordEncoder;

    /**
     * The model mapper
     *
     * @see gamestore.utils.mapper.ModelMapperConfig
     */
    private final ModelMapper mapper;

    /**
     * Gets user by id.
     *
     * @param id the id
     * @return the by id
     * @throws UserNotFoundException
     * @see TextConstants#USER_NOT_FOUND
     */
    @Async("asyncExecutor")
    public CompletableFuture<User> getById(Long id) {
        return CompletableFuture.completedFuture(userRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException(TextConstants.USER_NOT_FOUND)));
    }

    /**
     * Gets all users.
     *
     * @return all users
     */
    @Async("asyncExecutor")
    public CompletableFuture<List<User>> getAllUsers() {
        return CompletableFuture.completedFuture(userRepository.findAll());
    }

    /**
     * Register user user.
     *
     * @param register the user details send by the client
     * @return the user
     * @throws UsernameNotFoundException
     * @see UserRegisterBindingModel
     * @see UserRepository#getByUsername(String)
     * @see TextConstants#USERNAME_ALREADY_TAKEN
     * @see RoleService#getRole(String)
     */
    public User registerUser(UserRegisterBindingModel register) throws ExecutionException, InterruptedException {
        boolean userExists = userRepository
                .getByUsername(register.getUsername())
                .get()
                .isPresent();

        if (userExists) {
            // TODO if email not confirmed send confirmation email.
            throw new UsernameNotFoundException(TextConstants.USERNAME_ALREADY_TAKEN);
        }

        //add email verification
        User user = mapper.map(register, User.class);

        String encodedPassword = passwordEncoder.encode(register.getPassword());
        user.setPassword(encodedPassword);

        Role role = roleService.getRole("USER");
        user.getRoles().add(role);
        userRepository.save(user);

        return user;
    }


    /**
     * @param username
     * @return
     * @throws UsernameNotFoundException
     * @see TextConstants#USER_NOT_FOUND
     * @see UserRepository#getByUsername(String)
     */
    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        return userRepository
                .getByUsername(username)
                .get()
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format(TextConstants.USERNAME_NOT_FOUND, username)
                ));
    }
}
