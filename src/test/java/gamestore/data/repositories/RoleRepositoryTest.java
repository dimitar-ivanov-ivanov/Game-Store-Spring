package gamestore.data.repositories;

import gamestore.models.entities.security.Role;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class RoleRepositoryTest {

    @Autowired
    private RoleRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void roleShouldExistByName() {
        //given
        String name = "ADMIN";
        Role role = new Role(name);

        underTest.save(role);

        //when
        boolean exists = underTest
                .getByName(name)
                .isPresent();

        //then
        assertThat(exists)
                .isTrue();
    }

    @Test
    void roleShouldNotExistByName() {
        //given
        String name = "ADMIN_NEW";

        //when
        boolean exists = underTest
                .getByName(name)
                .isPresent();

        //then
        assertThat(exists)
                .isFalse();
    }
}