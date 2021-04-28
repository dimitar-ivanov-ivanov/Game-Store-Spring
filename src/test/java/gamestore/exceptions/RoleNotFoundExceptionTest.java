package gamestore.exceptions;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class RoleNotFoundExceptionTest {

    private final String MESSAGE =
            "Role ADMIN not found";

    @Test
    public void throwsDataOutOfRange() {
        try {
            throw new RoleNotFoundException(MESSAGE);
        } catch (RoleNotFoundException ex) {
            assertThat(ex)
                    .isInstanceOf(RoleNotFoundException.class)
                    .hasMessage(MESSAGE);
        }
    }
}