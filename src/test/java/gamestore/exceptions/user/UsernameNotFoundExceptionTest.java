package gamestore.exceptions.user;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UsernameNotFoundExceptionTest {

    private final String MESSAGE = "Thе birth date is out of range.";

    @Test
    public void throwsDataOutOfRange() {
        try {
            throw new UsernameNotFoundException
                    (MESSAGE);
        } catch (UsernameNotFoundException ex) {
            assertThat(ex)
                    .isInstanceOf(UsernameNotFoundException.class)
                    .hasMessage(MESSAGE);
        }
    }
}