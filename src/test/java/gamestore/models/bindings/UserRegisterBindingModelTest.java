package gamestore.models.bindings;

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
    void shouldThrowViolationWhenFirstNameIsBlank() {

    }

    @Test
    void shouldThrowViolationWhenLastNameIsBlank() {

    }

    @Test
    void shouldThrowViolationWhenBirthdateIsInTheFuture() {

    }

    @Test
    void shouldThrowViolationWhenBirthdateIsNull() {

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