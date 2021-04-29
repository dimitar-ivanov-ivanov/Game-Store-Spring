package gamestore.utils.validators;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class NumberValidatorTest {

    private final int low = 10;
    private final int high = 50;
    private final int inRange = 25;
    private final int outOfRange = 55;

    @Test
    void testInRange() {
        assertThat(NumberValidator.numberInRange(inRange, low, high))
                .isTrue();
    }

    @Test
    void testOutOfRange() {
        assertThat(NumberValidator.numberInRange(outOfRange, low, high))
                .isFalse();
    }

}