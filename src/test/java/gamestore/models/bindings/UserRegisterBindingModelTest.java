package gamestore.models.bindings;

import gamestore.models.enums.Gender;
import gamestore.utils.constants.TextConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@JsonTest
class UserRegisterBindingModelTest {

    @Autowired
    private JacksonTester<UserRegisterBindingModel> json;

    ValidatorFactory factory;
    Validator validator;

    private UserRegisterBindingModel underTest;

    private final String USERNAME_VALID = "niKolaaa";
    private final String EMAIL_VALID = "nikola@abv.bg";
    private final String PASSWORD_VALID = "A5a381bcacA";
    private final String FIRST_NAME_VALID = "nikola";
    private final String LAST_NAME_VALID = "petrov";
    private final String BIRTH_DATE_STRING = "1980-05-03";
    private final String GENDER_VALID = "MALE";
    private final LocalDate BIRTH_DATE_VALID = LocalDate.of(1980, 5, 3);

    private final String JSON_TO_DESERIALIZE =
            "{\n" +
                    "        \"username\": \"" + USERNAME_VALID + "\",\n" +
                    "        \"email\": \"" + EMAIL_VALID + "\",\n" +
                    "        \"password\": \"" + PASSWORD_VALID + "\",\n" +
                    "        \"matchingPassword\": \"" + PASSWORD_VALID + "\",\n" +
                    "        \"firstName\": \"" + FIRST_NAME_VALID + "\",\n" +
                    "        \"lastName\": \"" + LAST_NAME_VALID + "\",\n" +
                    "        \"birthDate\": \"" + BIRTH_DATE_STRING + "\",\n" +
                    "        \"gender\": \"" + GENDER_VALID + "\"\n" +
                    "}";

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
    void shouldThrowViolationWhenFirstNameIsEmpty() {
        //given
        underTest.setFirstName("");

        //when
        Set<ConstraintViolation<UserRegisterBindingModel>> violations =
                validator.validate(underTest);

        //then
        assertThat(violations.size())
                .isEqualTo(1);

        assertThat(violations.iterator().next().getMessage())
                .isEqualTo("first " + TextConstants.NAME_CANNOT_BE_BLANK);
    }

    @Test
    void shouldThrowViolationWhenFirstNameIsNull() {
        //given
        underTest.setFirstName(null);

        //when
        Set<ConstraintViolation<UserRegisterBindingModel>> violations =
                validator.validate(underTest);

        //then
        assertThat(violations.size())
                .isEqualTo(1);

        assertThat(violations.iterator().next().getMessage())
                .isEqualTo("first " + TextConstants.NAME_CANNOT_BE_BLANK);
    }

    @Test
    void shouldThrowViolationWhenLastNameIsEmpty() {
        //given
        underTest.setLastName("");

        //when
        Set<ConstraintViolation<UserRegisterBindingModel>> violations =
                validator.validate(underTest);

        //then
        assertThat(violations.size())
                .isEqualTo(1);

        assertThat(violations.iterator().next().getMessage())
                .isEqualTo("last " + TextConstants.NAME_CANNOT_BE_BLANK);
    }

    @Test
    void shouldThrowViolationWhenLastNameIsNull() {
        //given
        underTest.setLastName(null);

        //when
        Set<ConstraintViolation<UserRegisterBindingModel>> violations =
                validator.validate(underTest);

        //then
        assertThat(violations.size())
                .isEqualTo(1);

        assertThat(violations.iterator().next().getMessage())
                .isEqualTo("last " + TextConstants.NAME_CANNOT_BE_BLANK);
    }

    @Test
    void shouldThrowViolationWhenBirthdateIsInTheFuture() {
        //given
        underTest.setBirthDate(
                LocalDate.of(2040, 3, 2)
        );

        //when
        Set<ConstraintViolation<UserRegisterBindingModel>> violations =
                validator.validate(underTest);

        //then
        assertThat(violations.size())
                .isEqualTo(1);

        assertThat(violations.iterator().next().getMessage())
                .isEqualTo("must be a past date");
    }

    @Test
    void shouldThrowViolationWhenBirthdateIsNull() {
        //given
        underTest.setBirthDate(null);

        //when
        Set<ConstraintViolation<UserRegisterBindingModel>> violations =
                validator.validate(underTest);

        //then
        assertThat(violations.size())
                .isEqualTo(1);

        assertThat(violations.iterator().next().getMessage())
                .isEqualTo("must not be null");
    }

    @Test
    void shouldThrowViolationWhenPasswordsDontMatch() {
        underTest.setMatchingPassword(PASSWORD_VALID + "aa");

        //when
        Set<ConstraintViolation<UserRegisterBindingModel>> violations
                = validator.validate(underTest);

        //then
        assertThat(violations.size())
                .isEqualTo(1);

        assertThat(violations.iterator().next().getMessage())
                .isEqualTo("Password don't match.");
    }

    @Test
    void userNameDeserializes() throws IOException {
        assertThat(this.json.parseObject(JSON_TO_DESERIALIZE).getUsername())
                .isEqualTo(USERNAME_VALID);
    }

    @Test
    void emailDeserializes() throws IOException {
        assertThat(this.json.parseObject(JSON_TO_DESERIALIZE).getEmail())
                .isEqualTo(EMAIL_VALID);
    }

    @Test
    void passwordDeserializes() throws IOException {
        assertThat(this.json.parseObject(JSON_TO_DESERIALIZE).getPassword())
                .isEqualTo(PASSWORD_VALID);
    }

    @Test
    void matchingPasswordDeserializes() throws IOException {
        assertThat(this.json.parseObject(JSON_TO_DESERIALIZE).getMatchingPassword())
                .isEqualTo(PASSWORD_VALID);
    }

    @Test
    void firstNameDeserializes() throws IOException {
        assertThat(this.json.parseObject(JSON_TO_DESERIALIZE).getFirstName())
                .isEqualTo(FIRST_NAME_VALID);
    }

    @Test
    void lastNameDeserializes() throws IOException {
        assertThat(this.json.parseObject(JSON_TO_DESERIALIZE).getLastName())
                .isEqualTo(LAST_NAME_VALID);
    }

    @Test
    void birthDateDeserializes() throws IOException {
        assertThat(this.json.parseObject(JSON_TO_DESERIALIZE).getBirthDate())
                .isEqualTo(BIRTH_DATE_VALID);
    }

    @Test
    void genderDeserializes() throws IOException {
        assertThat(this.json.parseObject(JSON_TO_DESERIALIZE).getGender())
                .isEqualTo(Gender.valueOf(GENDER_VALID));
    }
}