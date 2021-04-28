package gamestore.exceptions;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class InvalidOrExpiredJwtTokenExceptionTest {

    private final String MESSAGE =
            "Thе jwt token is invalid.";

    @Test
    public void throwsDataOutOfRange() {
        try {
            throw new InvalidOrExpiredJwtTokenException
                    (MESSAGE);
        } catch (InvalidOrExpiredJwtTokenException ex) {
            assertThat(ex)
                    .isInstanceOf(InvalidOrExpiredJwtTokenException.class)
                    .hasMessage(MESSAGE);
        }
    }
}