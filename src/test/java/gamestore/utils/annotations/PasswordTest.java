package gamestore.utils.annotations;

import gamestore.models.bindings.UserRegisterBindingModel;
import gamestore.models.enums.Gender;
import gamestore.utils.constants.NumberConstants;
import gamestore.utils.constants.TextConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDate;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PasswordTest {

    ValidatorFactory factory;
    Validator validator;

    private UserRegisterBindingModel underTest;

    private final String USERNAME_VALID = "niKolaaa";
    private final String EMAIL_VALID = "nikola@abv.bg";
    private final String PASSWORD_VALID = "A5a381bcacA";
    private final String FIRST_NAME_VALID = "nikola";
    private final String LAST_NAME_VALID = "siderov";
    private final LocalDate BIRTH_DATE_VALID = LocalDate.of(1980, 5, 3);

    private final String PASSWORD_TOO_SHORT = "1234";
    private final String PASSWORD_TOO_LONG =
            "1234".repeat(NumberConstants.MAX_PASSWORD_LENGTH);

    private final String PASSWORD_NO_LOWER_LETTER =
            "AAA2134AA";

    private final String PASSWORD_NO_UPPER_LETTER =
            "AAA2134AA".toLowerCase();

    private final String PASSWORD_NO_DIGIT =
            "aaaaAAAdddDD";

    @BeforeEach
    void setUp() {

        factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        underTest = new UserRegisterBindingModel(
                USERNAME_VALID,
                EMAIL_VALID,
                PASSWORD_VALID,
                PASSWORD_VALID,
                FIRST_NAME_VALID,
                LAST_NAME_VALID,
                BIRTH_DATE_VALID,
                Gender.MALE
        );
    }

    @Test
    void shouldThrowViolationWhenPasswordIsNull() {
        //given
        underTest.setPassword(null);

        //when
        Set<ConstraintViolation<UserRegisterBindingModel>> violations =
                validator.validate(underTest);

        //then
        assertThat(violations.size())
                .isEqualTo(1);

        assertThat(violations.iterator().next().getMessage())
                .isEqualTo(
                        String.format(
                                TextConstants.DATA_CANNOT_BE_NULL,
                                "Password"
                        )
                );
    }

    @Test
    void shouldThrowViolationWhenPasswordIsTooLong() {
        //given
        underTest.setPassword(PASSWORD_TOO_LONG);

        //when
        Set<ConstraintViolation<UserRegisterBindingModel>> violations =
                validator.validate(underTest);

        //then
        assertThat(violations.size())
                .isEqualTo(1);

        assertThat(violations.iterator().next().getMessage())
                .isEqualTo(
                        String.format(
                                TextConstants.DATA_TOO_LONG,
                                "Password",
                                NumberConstants.MAX_PASSWORD_LENGTH
                        )
                );
    }

    @Test
    void shouldThrowViolationWhenPasswordIsTooShort() {
        //given
        underTest.setPassword(PASSWORD_TOO_SHORT);

        //when
        Set<ConstraintViolation<UserRegisterBindingModel>> violations =
                validator.validate(underTest);

        //then
        assertThat(violations.size())
                .isEqualTo(1);

        assertThat(violations.iterator().next().getMessage())
                .isEqualTo(
                        String.format(
                                TextConstants.DATA_TOO_SHORT,
                                "Password",
                                NumberConstants.MIN_PASSWORD_LENGTH
                        )
                );
    }

    @Test
    void shouldThrowViolationWhenPasswordDoesNotContainLowerLetter() {
        //given
        underTest.setPassword(PASSWORD_NO_LOWER_LETTER);

        //when
        Set<ConstraintViolation<UserRegisterBindingModel>> violations =
                validator.validate(underTest);

        //then
        assertThat(violations.size())
                .isEqualTo(1);

        assertThat(violations.iterator().next().getMessage())
                .isEqualTo(
                        String.format(
                                TextConstants.DATA_SHOULD_CONTAIN_LOWERCASE_LETTER,
                                "Password"
                        )
                );
    }

    @Test
    void shouldThrowViolationWhenPasswordDoesNotContainUpperLetter() {
        //given
        underTest.setPassword(PASSWORD_NO_UPPER_LETTER);

        //when
        Set<ConstraintViolation<UserRegisterBindingModel>> violations =
                validator.validate(underTest);

        //then
        assertThat(violations.size())
                .isEqualTo(1);

        assertThat(violations.iterator().next().getMessage())
                .isEqualTo(
                        String.format(
                                TextConstants.DATA_SHOULD_CONTAIN_UPPERCASE_LETTER,
                                "Password"
                        )
                );
    }

    @Test
    void shouldThrowViolationWhenPasswordDoesNotContainDigit() {
        //given
        underTest.setPassword(PASSWORD_NO_DIGIT);

        //when
        Set<ConstraintViolation<UserRegisterBindingModel>> violations =
                validator.validate(underTest);

        //then
        assertThat(violations.size())
                .isEqualTo(1);

        assertThat(violations.iterator().next().getMessage())
                .isEqualTo(
                        String.format(
                                TextConstants.DATA_SHOULD_CONTAIN_DIGIT,
                                "Password"
                        )
                );
    }

    //TODO: Test not matching password

}
