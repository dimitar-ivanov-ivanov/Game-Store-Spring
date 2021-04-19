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

public class EmailTest {
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

    private final String EMAIL_HOSTNAME_TOO_LONG =
            "dimitar" + "@abv.bg".repeat(NumberConstants.MAX_EMAIl_HOSTNAME_LENGTH);

    private final String EMAIL_HOSTNAME_TOO_SHORT = "dimitar";

    private final String[] BAD_EMAILS = {
            "9apesho@gmail.com",
            "a$sassho@aa.com",
            "aa--@gmail.com",
            "dimitar@ abv.bg",
            "dimitar@-abv.bg",
            "dimitar@a$bv.bg",
            "dimitar@abv.bg."
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
    void shouldThrowViolationWhenEmailIsNull() {
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
    void shouldThrowViolationWhenEmailUsernameIsTooShort() {
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
    void shouldThrowViolationWhenEmailUsernameIsTooLong() {
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
    void shouldThrowViolationWhenEmailHostNameIsTooShort() {
        model.setEmail(EMAIL_HOSTNAME_TOO_SHORT);
        Set<ConstraintViolation<UserRegisterBindingModel>> violations =
                validator.validate(model);

        assertThat(violations.size())
                .isEqualTo(1);

        assertThat(violations.iterator().next().getMessage())
                .isEqualTo(String.format(
                        TextConstants.EMAIL_USERNAME_TOO_SHORT,
                        NumberConstants.MIN_EMAIl_HOSTNAME_LENGTH
                ));
    }

    @Test
    void shouldThrowViolationWhenEmailHostNameIsTooLong() {
        model.setEmail(EMAIL_HOSTNAME_TOO_LONG);
        Set<ConstraintViolation<UserRegisterBindingModel>> violations =
                validator.validate(model);

        assertThat(violations.size())
                .isEqualTo(1);

        assertThat(violations.iterator().next().getMessage())
                .isEqualTo(String.format(
                        TextConstants.DATA_TOO_LONG,
                        "hostname",
                        NumberConstants.MAX_EMAIl_HOSTNAME_LENGTH
                ));
    }

    @Test
    void showThrowViolationForEveryBadEmail() {
        for (String badEmail : BAD_EMAILS) {
            model.setEmail(badEmail);

            Set<ConstraintViolation<UserRegisterBindingModel>> violations =
                    validator.validate(model);

            assertThat(violations.size())
                    .isEqualTo(1);

            assertThat(violations.iterator().next().getMessage())
                    .isEqualTo(TextConstants.INVALID_EMAIL_FORMAT);
        }
    }
}
