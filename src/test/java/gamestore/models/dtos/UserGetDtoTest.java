package gamestore.models.dtos;

import gamestore.models.entities.user.UserBoughtGame;
import gamestore.models.entities.user.UserGameId;
import gamestore.models.enums.Gender;
import gamestore.utils.formatters.LocalDateFormatter;
import org.assertj.core.util.Sets;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import static net.javacrumbs.jsonunit.fluent.JsonFluentAssert.assertThatJson;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Locale;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@JsonTest // disables auto-configuration and apply configuration only relevant to json test
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

    public void filedBoughtGamesSerialize() throws IOException {

        /*
        userGetDto.getBoughtGames().add(new UserBoughtGame(
                new UserGameId(),

                ));

        assertThatJson(json.write(userGetDto).getJson())
                .node("boughtGames")
                .isEqualTo("");

         */
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

    @Test
    public void firstNameDeserializes() throws IOException {
        assertThat(this.json.parseObject(JSON_TO_DESERIALIZE).getFirstName())
                .isEqualTo(FIRST_NAME);
    }

    @Test
    public void lastNameDeserializes() throws IOException {
        assertThat(this.json.parseObject(JSON_TO_DESERIALIZE).getLastName())
                .isEqualTo(LAST_NAME);
    }

    @Test
    public void birthDateDeserializes() throws IOException {
        assertThat(this.json.parseObject(JSON_TO_DESERIALIZE).getBirthDate())
                .isEqualTo(BIRTHDATE);
    }

    @Test
    public void emailDeserializes() throws IOException {
        assertThat(this.json.parseObject(JSON_TO_DESERIALIZE).getEmail())
                .isEqualTo(EMAIL);
    }

    @Test
    public void usernameDeserializes() throws IOException {
        assertThat(this.json.parseObject(JSON_TO_DESERIALIZE).getUsername())
                .isEqualTo(USERNAME);
    }

    @Test
    public void genderDeserializes() throws IOException {
        assertThat(this.json.parseObject(JSON_TO_DESERIALIZE).getGender())
                .isEqualTo(Gender.valueOf(GENDER));
    }

    @Test
    public void emptyBoughGamesDeserialize() throws IOException {
        assertThat(this.json.parseObject(JSON_TO_DESERIALIZE).getBoughtGames())
                .isEqualTo(Sets.newHashSet());
    }

    @Test
    public void emptyWishlistGamesDeserialize() throws IOException {
        assertThat(this.json.parseObject(JSON_TO_DESERIALIZE).getWishlistGames())
                .isEqualTo(Sets.newHashSet());
    }

    @Test
    public void emptyGameBadgesDeserialize() throws IOException {
        assertThat(this.json.parseObject(JSON_TO_DESERIALIZE).getGameBadges())
                .isEqualTo(Sets.newHashSet());
    }

    @Test
    public void emptyAchievementsDeserialize() throws IOException {
        assertThat(this.json.parseObject(JSON_TO_DESERIALIZE).getAchievements())
                .isEqualTo(Sets.newHashSet());
    }
}