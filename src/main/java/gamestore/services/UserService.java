package gamestore.services;

import gamestore.constants.Messages;
import gamestore.exceptions.UserNotFoundException;
import gamestore.models.Role;
import gamestore.models.User;
import gamestore.models.bindings.UserRegisterBindingModel;
import gamestore.repositories.UserRepository;
import gamestore.validators.DateValidator;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
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
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(Messages.USER_NOT_FOUND));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void registerUser(UserRegisterBindingModel register) {
        boolean userExists = userRepository
                .findByUsername(register.getUsername())
                .isPresent();

        if (userExists) {
            // TODO check of attributes are the same and
            // TODO if email not confirmed send confirmation email.
            throw new UsernameNotFoundException(Messages.USERNAME_ALREADY_TAKEN);
        }

        DateValidator.validateSeparateDate(
                register.getBirthYear(),
                register.getBirthMonth(),
                register.getBirthDay()
        );

        String encodedPassword = passwordEncoder.encode(register.getPassword());
        register.setPassword(encodedPassword);

        //add email verification
        User user = mapper.map(register, User.class);

        Role role = roleService.getRole("USER");
        user.getRoles().add(role);
        userRepository.save(user);
    }


    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        return userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format(Messages.USERNAME_NOT_FOUND, username)
                ));
    }
}
