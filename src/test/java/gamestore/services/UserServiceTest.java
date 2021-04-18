package gamestore.services;

import com.google.common.base.Optional;
import gamestore.exceptions.user.UserNotFoundException;
import gamestore.models.bindings.UserRegisterBindingModel;
import gamestore.models.entities.user.User;
import gamestore.models.enums.Gender;
import gamestore.repositories.UserRepository;
import gamestore.utils.constants.TextConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

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

        //TODO: finish after testing register user


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

    }


    @Test
    void loadUserByUsername() {
    }
}