package gamestore.utils.exceptions.user;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class UserBirthdayNotValidExceptionTest {

    private final String MESSAGE = "Thе birth date is out of range.";

    @Test
    public void throwsDataOutOfRange() {
        try {
            throw new UserBirthdayNotValidException
                    (MESSAGE);
        } catch (UserBirthdayNotValidException ex) {
            assertThat(ex)
                    .isInstanceOf(UserBirthdayNotValidException.class)
                    .hasMessage(MESSAGE);
        }
    }
}