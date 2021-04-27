package gamestore.models.bindings;

import gamestore.models.entities.user.User;
import gamestore.models.enums.Gender;
import gamestore.utils.annotations.email.Email;
import gamestore.utils.constants.NumberConstants;
import gamestore.utils.constants.TextConstants;
import org.apache.tomcat.jni.Local;
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
    void shouldThrowViolationWhenFirstNameIsEmpty() {
        //given
        model.setFirstName("");

        //when
        Set<ConstraintViolation<UserRegisterBindingModel>> violations =
                validator.validate(model);

        //then
        assertThat(violations.size())
                .isEqualTo(1);

        assertThat(violations.iterator().next().getMessage())
                .isEqualTo("first " + TextConstants.NAME_CANNOT_BE_BLANK);
    }

    @Test
    void shouldThrowViolationWhenFirstNameIsNull() {
        //given
        model.setFirstName(null);

        //when
        Set<ConstraintViolation<UserRegisterBindingModel>> violations =
                validator.validate(model);

        //then
        assertThat(violations.size())
                .isEqualTo(1);

        assertThat(violations.iterator().next().getMessage())
                .isEqualTo("first " + TextConstants.NAME_CANNOT_BE_BLANK);
    }

    @Test
    void shouldThrowViolationWhenLastNameIsEmpty() {
        //given
        model.setLastName("");

        //when
        Set<ConstraintViolation<UserRegisterBindingModel>> violations =
                validator.validate(model);

        //then
        assertThat(violations.size())
                .isEqualTo(1);

        assertThat(violations.iterator().next().getMessage())
                .isEqualTo("last " + TextConstants.NAME_CANNOT_BE_BLANK);
    }

    @Test
    void shouldThrowViolationWhenLastNameIsNull() {
        //given
        model.setLastName(null);

        //when
        Set<ConstraintViolation<UserRegisterBindingModel>> violations =
                validator.validate(model);

        //then
        assertThat(violations.size())
                .isEqualTo(1);

        assertThat(violations.iterator().next().getMessage())
                .isEqualTo("last " + TextConstants.NAME_CANNOT_BE_BLANK);
    }

    @Test
    void shouldThrowViolationWhenBirthdateIsInTheFuture() {
        model.setBirthDate(
                LocalDate.of(2040, 3, 2)
        );

        //when
        Set<ConstraintViolation<UserRegisterBindingModel>> violations =
                validator.validate(model);

        //then
        assertThat(violations.size())
                .isEqualTo(1);

        assertThat(violations.iterator().next().getMessage())
                .isEqualTo("must be a past date");
    }

    @Test
    void shouldThrowViolationWhenBirthdateIsNull() {
        model.setBirthDate(null);

        //when
        Set<ConstraintViolation<UserRegisterBindingModel>> violations =
                validator.validate(model);

        //then
        assertThat(violations.size())
                .isEqualTo(1);

        assertThat(violations.iterator().next().getMessage())
                .isEqualTo("must not be null");
    }


    //test deserialization
}