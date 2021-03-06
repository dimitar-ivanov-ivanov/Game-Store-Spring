package gamestore.utils.exceptions;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class DataOutOfRangeExceptionTest {

    private final String MESSAGE = "This data is out of range.";

    @Test
    public void throwsDataOutOfRange() {
        try {
            throw new DataOutOfRangeException(MESSAGE);
        } catch (DataOutOfRangeException ex) {
            assertThat(ex)
                    .isInstanceOf(DataOutOfRangeException.class)
                    .hasMessage(MESSAGE);
        }
    }
}