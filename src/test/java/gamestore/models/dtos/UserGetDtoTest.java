package gamestore.models.dtos;

import gamestore.models.enums.Gender;
import gamestore.utils.formatters.LocalDateFormatter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;

import static net.javacrumbs.jsonunit.fluent.JsonFluentAssert.assertThatJson;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Locale;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@JsonTest // disables auto-configuration and apply configuration only relevent to json test
public class UserGetDtoTest {

    @Autowired
    private JacksonTester<UserGetDto> json;

    private static final LocalDateFormatter localDateFormatter =
            new LocalDateFormatter();

    private static final String FIRST_NAME = "Stoqn";
    private static final String LAST_NAME = "Ivanov";
    private static final String BIRTHDATE = "1999-02-20";
    private static final String USERNAME = "stQn";
    private static final String EMAIL = "stoqn-ivanov@abv.bg";
    private static final String GENDER = "MALE";
    private static final String EMPTY_ARRAY = "[]";

    private final String JSON_TO_DESERIALIZE =
            "  {\"firstName\": \"" + FIRST_NAME + "\",\n" +
                    "    \"lastName\": \"" + LAST_NAME + "\",\n" +
                    "    \"birthDate\": \"" + BIRTHDATE + "\",\n" +
                    "    \"username\": \"" + USERNAME + "\",\n" +
                    "    \"email\": \"" + EMAIL + "\",\n" +
                    "    \"gender\": \"" + GENDER + "\",\n" +
                    "    \"boughtGames\": " + EMPTY_ARRAY + ",\n" +
                    "    \"wishlistGames\": " + EMPTY_ARRAY + ",\n" +
                    "    \"gameBadges\": " + EMPTY_ARRAY + ",\n" +
                    "    \"achievements\": " + EMPTY_ARRAY + "}";

    private static UserGetDto userGetDto;

    private static LocalDate parseDate(final String dateString) {
        try {
            return localDateFormatter.parse(dateString, Locale.ROOT);
        } catch (final ParseException e) {
            return LocalDate.now();
        }
    }

    @BeforeAll
    public static void setUp() {
        userGetDto = new UserGetDto(
                FIRST_NAME,
                LAST_NAME,
                parseDate(BIRTHDATE),
                USERNAME,
                EMAIL,
                Gender.valueOf(GENDER)
        );
    }

    @Test
    public void firstNameSerializes() throws IOException {
        assertThatJson(json.write(userGetDto).getJson())
                .node("firstName")
                .isEqualTo(FIRST_NAME);
    }

    @Test
    public void secondNameSerializes() throws IOException {
        assertThatJson(json.write(userGetDto).getJson())
                .node("lastName")
                .isEqualTo(LAST_NAME);
    }

    @Test
    public void birthDateSerializes() throws IOException {
        assertThatJson(this.json.write(userGetDto).getJson())
                .node("birthDate")
                .isEqualTo(BIRTHDATE);
    }

    @Test
    public void userNameSerializes() throws IOException {
        assertThatJson(json.write(userGetDto).getJson())
                .node("username")
                .isEqualTo(USERNAME);
    }

    @Test
    public void emailSerializes() throws IOException {
        assertThatJson(json.write(userGetDto).getJson())
                .node("email")
                .isEqualTo(EMAIL);
    }

    @Test
    public void emptyBoughtGamesSerialize() throws IOException {
        assertThatJson(json.write(userGetDto).getJson())
                .node("boughtGames")
                .isEqualTo(EMPTY_ARRAY);
    }

    @Test
    public void emptyWishlistGamesSerialize() throws IOException {
        assertThatJson(json.write(userGetDto).getJson())
                .node("wishlistGames")
                .isEqualTo(EMPTY_ARRAY);
    }

    @Test
    public void emptyGameBadgesSerialize() throws IOException {
        assertThatJson(json.write(userGetDto).getJson())
                .node("gameBadges")
                .isEqualTo(EMPTY_ARRAY);
    }

    @Test
    public void emptyAchievementsSerialize() throws IOException {
        assertThatJson(json.write(userGetDto).getJson())
                .node("achievements")
                .isEqualTo(EMPTY_ARRAY);
    }


}
