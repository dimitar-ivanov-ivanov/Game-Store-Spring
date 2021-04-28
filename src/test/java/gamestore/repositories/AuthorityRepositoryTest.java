package gamestore.repositories;

import gamestore.models.entities.security.Authority;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AuthorityRepositoryTest {

    @Autowired
    private AuthorityRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void authorityShouldExistByName() {
        //given
        String name = "user:read";
        Authority auth = new Authority(name);

        underTest.save(auth);

        //when
        boolean exists = underTest
                .getByName(name)
                .isPresent();

        //then
        assertThat(exists)
                .isTrue();
    }

    @Test
    void authorityShouldNotByName() {
        //given
        String name = "user:read";

        //when
        boolean exists = underTest
                .getByName(name)
                .isPresent();

        //then
        assertThat(exists)
                .isFalse();
    }
}