package gamestore.annotations;

import gamestore.models.bindings.UserRegisterBindingModel;
import gamestore.models.enums.Gender;
import gamestore.utils.constants.NumberConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDate;

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

    }

    @Test
    void shouldThrowViolationWhenUsernameIsTooShort() {

    }

    @Test
    void shouldThrowViolationWhenUsernameIsTooLong() {

    }

    @Test
    void shouldThrowViolationWhenUsernameDoesNotContainLowerLetter() {

    }

    @Test
    void shouldThrowViolationWhenUsernameDoesNotContainUpperLetter() {

    }
}
