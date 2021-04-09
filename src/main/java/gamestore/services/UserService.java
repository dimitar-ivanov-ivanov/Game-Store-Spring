package gamestore.services;

import gamestore.constants.Messages;
import gamestore.dtos.RegisterRequestBindingModel;
import gamestore.exceptions.UserNotFoundException;
import gamestore.models.Role;
import gamestore.models.User;
import gamestore.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Service
@Transactional
public class UserService implements UserDetailsService {

    private final RoleService roleService;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;


    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(Messages.USER_NOT_FOUND));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void registerUser(RegisterRequestBindingModel register) {
        boolean userExists = userRepository
                .findByUsername(register.getUsername())
                .isPresent();

        if (userExists) {
            // TODO check of attributes are the same and
            // TODO if email not confirmed send confirmation email.
            throw new UsernameNotFoundException(Messages.USERNAME_ALREADY_TAKEN);
        }

        //validate date

        String encodedPassword = passwordEncoder
                .encode(register.getPassword());


        User user = new User(
                register.getFirstName(),
                register.getLastName(),
                LocalDate.of(register.getBirthYear(), register.getBirthMonth(), register.getBirthDay()),
                register.getUsername(),
                register.getEmail(),
                encodedPassword,
                false,
                false,
                true
        );

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
