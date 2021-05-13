package gamestore.data.services;

import gamestore.utils.exceptions.user.UserNotFoundException;
import gamestore.utils.exceptions.user.UsernameNotFoundException;
import gamestore.models.bindings.UserRegisterBindingModel;
import gamestore.models.entities.user.User;
import gamestore.models.enums.Gender;
import gamestore.data.repositories.UserRepository;
import gamestore.utils.constants.TextConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class UserServiceTest {

    @Mock
    private RoleService roleService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    private ModelMapper mapper = new ModelMapper();

    private UserService underTest;

    private UserRegisterBindingModel newUser;

    private final String NEW_USER_ROLE = "USER";

    @BeforeEach
    void setUp() {
        underTest = new UserService(
                roleService,
                userRepository,
                passwordEncoder,
                mapper
        );

        newUser = new UserRegisterBindingModel(
                "niKolaaa",
                "nikola@abv.bg",
                "A5a381bcacA",
                "A5a381bcacA",
                "nikola",
                "siderov",
                LocalDate.of(1999, 2, 3),
                Gender.MALE
        );
    }

    @Test
    @Disabled
    void shouldFindUserById() {
        //given
        long id = 1;
        User user = new User(
                "dimitar",
                "ivanov",
                LocalDate.of(1999, 12, 5),
                "Dimitar",
                "dimitar.i.ivanov@abv.bg",
                "A_35aa51A",
                Gender.MALE
        );

        userRepository.save(user);

        //when
        underTest.getById(id);

        //then
        verify(userRepository)
                .findById(id);
    }

    @Test
    void shouldThrowUserNotFoundException() {
        //given
        long id = 1;

        //when
        //then
        assertThatThrownBy(() -> underTest.getById(id))
                .isInstanceOf(UserNotFoundException.class)
                .hasMessageContaining(TextConstants.USER_NOT_FOUND);

        verify(userRepository).findById(id);
    }

    @Test
    void shouldGetAllUsers() {
        //when
        underTest.getAllUsers();

        //then
        verify(userRepository).findAll();
    }

    @Test
    void shouldRegisterUser() {
        //given
        //when
        underTest.registerUser(newUser);

        //then
        verify(passwordEncoder).encode(newUser.getPassword());

        verify(roleService).getRole(NEW_USER_ROLE);

        ArgumentCaptor<User> userArgumentCaptor =
                ArgumentCaptor.forClass(User.class);

        verify(userRepository)
                .save(userArgumentCaptor.capture());

        User captureUser = userArgumentCaptor.getValue();

        assertThat(captureUser.equalsBindingModel(newUser))
                .isTrue();
    }

    @Test
    void shouldThrowExceptionWhenRegisteringExistingUser() {
        //given
        User user = new User(
                "dimitar",
                "ivanov",
                LocalDate.of(1999, 12, 5),
                "Dimitar",
                "dimitar.i.ivanov@abv.bg",
                "A_35aa51A",
                Gender.MALE
        );

        Optional<User> opt = Optional.of(user);

        when(userRepository.getByUsername(anyString()))
                .thenReturn(opt);

        //when
        //then
        assertThatThrownBy(() ->
                underTest.registerUser(newUser))
                .isInstanceOf(UsernameNotFoundException.class)
                .hasMessageContaining(
                        TextConstants.USERNAME_ALREADY_TAKEN
                );


        verify(passwordEncoder, never())
                .encode(any());

        verify(roleService, never())
                .getRole(any());

        verify(userRepository, never())
                .save(any());
    }


    @Test
    void shouldLoadUserByUsername() {
        User user = new User(
                "dimitar",
                "ivanov",
                LocalDate.of(1999, 12, 5),
                "Dimitar",
                "dimitar.i.ivanov@abv.bg",
                "A_35aa51A",
                Gender.MALE
        );

        Optional<User> opt = Optional.of(user);

        when(userRepository.getByUsername(anyString())).thenReturn(opt);
        UserDetails userDetails = underTest.loadUserByUsername(anyString());

        assertThat("Dimitar")
                .isEqualTo(userDetails.getUsername());

        verify(userRepository)
                .getByUsername(anyString());
    }
}