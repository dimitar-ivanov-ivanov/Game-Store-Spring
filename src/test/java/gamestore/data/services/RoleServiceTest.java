package gamestore.data.services;

import gamestore.models.entities.security.Role;
import gamestore.data.repositories.RoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class RoleServiceTest {

    @Mock
    private RoleRepository roleRepository;

    private RoleService underTest;

    @BeforeEach
    void setUp() {
        underTest = new RoleService(
                roleRepository
        );
    }

    @Test
    void shouldGetRoleByName() {
        String name = "ADMIN";
        Role role = new Role(name);
        Optional<Role> opt = Optional.of(role);

        when(roleRepository.getByName(anyString()))
                .thenReturn(opt);
        Role res = underTest.getRole(anyString());

        assertThat(name)
                .isEqualTo(res.getName());

        verify(roleRepository)
                .getByName(anyString());
    }

}