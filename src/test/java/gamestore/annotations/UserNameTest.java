package gamestore.annotations;

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

public class UserNameTest {

    ValidatorFactory factory;
    Validator validator;

    private UserRegisterBindingModel model;

    private final String USERNAME_VALID = "niKolaaa";
    private final String EMAIL_VALID = "nikola@abv.bg";
    private final String PASSWORD_VALID = "A5a381bcacA";
    private final String FIRST_NAME_VALID = "nikola";
    private final String LAST_NAME_VALID = "siderov";
    private final LocalDate BIRTH_DATE_VALID = LocalDate.of(1980, 5, 3);

    private final String USERNAME_TOO_SHORT = "AA";
    private final String USERNAME_TOO_LONG =
            "Aa".repeat(NumberConstants.MAX_USERNAME_LENGTH);

    private final String USERNAME_NO_LOWER_LETTER = "AAA41AAA";
    private final String USERNAME_NO_UPPER_LETTER =
            USERNAME_NO_LOWER_LETTER.toLowerCase();

    private final String[] BAD_USERNAMES = {
            "22a2aA",
            "A5Aa55",
            "A!Aa-!$A",
            "-2aAAA-512AA-"
    };

    @BeforeEach
    void setUp() {

        factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        model = new UserRegisterBindingModel(
                USERNAME_VALID,
                EMAIL_VALID,
                PASSWORD_VALID,
                FIRST_NAME_VALID,
                LAST_NAME_VALID,
                BIRTH_DATE_VALID,
                Gender.MALE
        );
    }

    @Test
    void shouldThrowViolationWhenUsernameIsNull() {
        //given
        model.setUsername(null);

        //when
        Set<ConstraintViolation<UserRegisterBindingModel>> violations =
                validator.validate(model);

        //then
        assertThat(violations.size())
                .isEqualTo(1);

        assertThat(violations.iterator().next().getMessage())
                .isEqualTo(
                        String.format(
                                TextConstants.DATA_CANNOT_BE_NULL,
                                "User name"
                        )
                );
    }

    @Test
    void shouldThrowViolationWhenUsernameIsTooShort() {
        //given
        model.setUsername(USERNAME_TOO_SHORT);

        //when
        Set<ConstraintViolation<UserRegisterBindingModel>> violations =
                validator.validate(model);

        //then
        assertThat(violations.size())
                .isEqualTo(1);

        assertThat(violations.iterator().next().getMessage())
                .isEqualTo(
                        String.format(
                                TextConstants.DATA_TOO_SHORT,
                                "User name",
                                NumberConstants.MIN_USERNAME_LENGTH
                        )
                );
    }

    @Test
    void shouldThrowViolationWhenUsernameIsTooLong() {
        //given
        model.setUsername(USERNAME_TOO_LONG);

        //when
        Set<ConstraintViolation<UserRegisterBindingModel>> violations =
                validator.validate(model);

        //then
        assertThat(violations.size())
                .isEqualTo(1);

        assertThat(violations.iterator().next().getMessage())
                .isEqualTo(
                        String.format(
                                TextConstants.DATA_TOO_LONG,
                                "User name",
                                NumberConstants.MAX_USERNAME_LENGTH
                        )
                );
    }

    @Test
    void shouldThrowViolationWhenUsernameDoesNotContainLowerLetter() {
        //given
        model.setUsername(USERNAME_NO_LOWER_LETTER);

        //when
        Set<ConstraintViolation<UserRegisterBindingModel>> violations =
                validator.validate(model);

        //then
        assertThat(violations.size())
                .isEqualTo(1);

        assertThat(violations.iterator().next().getMessage())
                .isEqualTo(
                        String.format(
                                TextConstants.DATA_SHOULD_CONTAIN_LOWERCASE_LETTER,
                                "User name"
                        )
                );
    }

    @Test
    void shouldThrowViolationWhenUsernameDoesNotContainUpperLetter() {
        //given
        model.setUsername(USERNAME_NO_UPPER_LETTER);

        //when
        Set<ConstraintViolation<UserRegisterBindingModel>> violations =
                validator.validate(model);

        //then
        assertThat(violations.size())
                .isEqualTo(1);

        assertThat(violations.iterator().next().getMessage())
                .isEqualTo(
                        String.format(
                                TextConstants.DATA_SHOULD_CONTAIN_UPPERCASE_LETTER,
                                "User name"
                        )
                );
    }

    @Test
    void shouldThrowViolationForEveryBadUsername() {
        for (String badUsername : BAD_USERNAMES) {
            //given
            model.setUsername(badUsername);

            //when
            Set<ConstraintViolation<UserRegisterBindingModel>> violations =
                    validator.validate(model);

            //then
            assertThat(violations.size())
                    .isEqualTo(1);

            assertThat(violations.iterator().next().getMessage())
                    .isEqualTo(TextConstants.INVALID_USERNAME_FORMAT);
        }
    }
}
