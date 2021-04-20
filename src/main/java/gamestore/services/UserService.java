package gamestore.services;

import gamestore.exceptions.user.UsernameNotFoundException;
import gamestore.utils.constants.TextConstants;
import gamestore.exceptions.user.UserNotFoundException;
import gamestore.models.entities.security.Role;
import gamestore.models.entities.user.User;
import gamestore.models.bindings.UserRegisterBindingModel;
import gamestore.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@AllArgsConstructor
@Service
@Transactional
public class UserService implements UserDetailsService {

    private final RoleService roleService;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final ModelMapper mapper;

    public User getById(Long id) {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException(TextConstants.USER_NOT_FOUND));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User registerUser(UserRegisterBindingModel register) {
        boolean userExists = userRepository
                .getByUsername(register.getUsername())
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


    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        return userRepository
                .getByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format(TextConstants.USERNAME_NOT_FOUND, username)
                ));
    }
}
