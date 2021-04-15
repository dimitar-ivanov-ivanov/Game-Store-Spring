package gamestore.services;

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
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private RoleService roleService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private BCryptPasswordEncoder passwordEncoder;
    @Mock
    private ModelMapper mapper;

    private UserService underTest;

    @BeforeEach
    void setUp() {
        underTest = new UserService(
                roleService,
                userRepository,
                passwordEncoder,
                mapper
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
    }

    @Test
    void canGetAllUsers() {
        //when
        underTest.getAllUsers();

        //then
        verify(userRepository).findAll();
    }

    @Test
    void registerUser() {
        UserRegisterBindingModel model = new UserRegisterBindingModel(
                "Dimitar",
                "dimitar.i.ivanov@yahooc.com",
                "A_35aa51A",
                "Dimitar",
                "Ivanov",
                LocalDate.of(1999, 2, 3),
                Gender.MALE
        );
    }

    @Test
    void loadUserByUsername() {
    }
}