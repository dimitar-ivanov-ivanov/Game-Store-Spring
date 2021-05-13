package gamestore.data.repositories;

import gamestore.models.entities.user.User;
import gamestore.models.enums.Gender;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.time.LocalDate;
import java.util.concurrent.ExecutionException;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void userShouldExistByUsername() throws ExecutionException, InterruptedException {
        //given
        String username = "Dimitar";
        User user = new User(
                "dimitar",
                "ivanov",
                LocalDate.of(1999, 12, 5),
                username,
                "dimitar.i.ivanov@abv.bg",
                "A_35aa51A",
                Gender.MALE
        );

        underTest.save(user);

        //when
        boolean exists = underTest
                .getByUsername(username)
                .get()
                .isPresent();

        //then
        assertThat(exists)
                .isTrue();
    }

    @Test
    void userShouldNotExistsByUsername() throws ExecutionException, InterruptedException {
        //given
        String username = "Dimitar";

        //when
        boolean exists = underTest
                .getByUsername(username)
                .get()
                .isPresent();

        //then
        assertThat(exists)
                .isFalse();
    }

}