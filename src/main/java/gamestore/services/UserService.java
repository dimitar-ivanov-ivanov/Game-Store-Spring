package gamestore.services;

import gamestore.constants.Messages;
import gamestore.dtos.RegisterRequestBindingModel;
import gamestore.exceptions.UserBirthdayNotValidException;
import gamestore.exceptions.UserNotFoundException;
import gamestore.models.Role;
import gamestore.models.User;
import gamestore.repositories.UserRepository;
import gamestore.validators.NumberValidator;
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

        //validate date -> possible error: day is 31 but month only has 30 days
        validateBirthDate(register);

        String encodedPassword = passwordEncoder
                .encode(register.getPassword());


        //make it so that we use auto mapper
        //add email verification
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

    private void validateBirthDate(RegisterRequestBindingModel register) {
        boolean yearInRange = NumberValidator.numberInRange(register.getBirthYear(), 1900, LocalDate.now().getYear());
        boolean monthInRange = NumberValidator.numberInRange(register.getBirthYear(), 1, 12);
        boolean dayInRange = NumberValidator.numberInRange(register.getBirthYear(), 1, 31);

        if (!yearInRange) {
            throw new UserBirthdayNotValidException(String.format(
                    Messages.NOT_VALID, "user birth year"
            ));
        }
        if (!monthInRange) {
            throw new UserBirthdayNotValidException(String.format(
                    Messages.NOT_VALID, "user birth moth"
            ));
        }
        if (!dayInRange) {
            throw new UserBirthdayNotValidException(String.format(
                    Messages.NOT_VALID, "user birth day"
            ));
        }
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
