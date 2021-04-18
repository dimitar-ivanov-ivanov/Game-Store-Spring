package gamestore.models.bindings;

import gamestore.exceptions.user.UserNotFoundException;
import gamestore.models.entities.user.User;
import gamestore.models.enums.Gender;
import gamestore.utils.annotations.email.Email;
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
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class UserRegisterBindingModelTest {

    ValidatorFactory factory;
    Validator validator;

    private UserRegisterBindingModel model;

    private final String USERNAME_VALID = "niKolaaa";
    private final String EMAIL_VALID = "nikola@abv.bg";
    private final String PASSWORD_VALID = "A5a381bcacA";
    private final String FIRST_NAME_VALID = "nikola";
    private final String LAST_NAME_VALID = "siderov";
    private final LocalDate BIRTH_DATE_VALID = LocalDate.of(1980, 5, 3);

    private final String EMAIL_USERNAME_TOO_SHORT = "@abv.bg";
    private final String EMAIL_USERNAME_TOO_LONG =
            "a".repeat(NumberConstants.MAX_EMAIl_USERNAME_LENGTH + 1) + "@abv.bg";

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
    void shouldThrowExceptionWhenEmailIsNull() {
        model.setEmail(null);
        Set<ConstraintViolation<UserRegisterBindingModel>> violations =
                validator.validate(model);

        assertThat(violations.size())
                .isEqualTo(1);

        assertThat(violations.iterator().next().getMessage())
                .isEqualTo(String.format(
                        TextConstants.DATA_CANNOT_BE_NULL,
                        "Email"
                ));
    }

    @Test
    void shouldThrowExceptionWhenEmailUsernameIsTooShort() {
        model.setEmail(EMAIL_USERNAME_TOO_SHORT);
        Set<ConstraintViolation<UserRegisterBindingModel>> violations =
                validator.validate(model);

        assertThat(violations.size())
                .isEqualTo(1);

        assertThat(violations.iterator().next().getMessage())
                .isEqualTo(String.format(
                        TextConstants.EMAIL_USERNAME_TOO_SHORT,
                        NumberConstants.MIN_EMAIl_USERNAME_LENGTH
                ));
    }

    @Test
    void shouldThrowExceptionWhenEmailUsernameIsTooLong() {
        model.setEmail(EMAIL_USERNAME_TOO_LONG);
        Set<ConstraintViolation<UserRegisterBindingModel>> violations =
                validator.validate(model);

        assertThat(violations.size())
                .isEqualTo(1);

        assertThat(violations.iterator().next().getMessage())
                .isEqualTo(String.format(
                        TextConstants.EMAIL_DETAIL_TOO_LONG,
                        "username",
                        NumberConstants.MAX_EMAIl_USERNAME_LENGTH
                ));
    }

    @Test
    void shouldThrowExceptionWhenPasswordIsInvalid() {
        //for every one we have multiple cases
    }

    @Test
    void shouldThrowExceptionWhenUsernameIsInvalid() {

    }

    @Test
    void shouldThrowExceptionWhenFirstNameIsBlank() {

    }

    @Test
    void shouldThrowExceptionWhenLastNameIsBlank() {

    }

    @Test
    void shouldThrowExceptionWhenBirthdateIsInThePast() {

    }

    @Test
    void shouldThrowExceptionWhenBirthdateIsNull() {

    }


    @Test
    void testEquals() {
        UserRegisterBindingModel secondModel = new UserRegisterBindingModel(
                USERNAME_VALID,
                EMAIL_VALID,
                PASSWORD_VALID,
                FIRST_NAME_VALID,
                LAST_NAME_VALID,
                BIRTH_DATE_VALID,
                Gender.MALE
        );

        assertThat(model)
                .isEqualTo(secondModel);
    }
}