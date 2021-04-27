package gamestore.models.dtos;

import gamestore.models.enums.Gender;
import gamestore.utils.formatters.LocalDateFormatter;
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
import java.util.Set;

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
    private static final String EMAIL = "stoq-ivanov@abv.bg";
    private static final String GENDER = "MALE";
    private static final String EMPTY_ARRAY = "[]";

    private static UserGetDto userGetDto;

    private static final Set<UserFriendDto> friends = com.google.common.collect.Sets.newHashSet(
            new UserFriendDto("Pesh"),
            new UserFriendDto("Ivv"),
            new UserFriendDto("vvvV")
    );

    private static final Set<UserBoughtGameDto> boughtGames = com.google.common.collect.Sets.newHashSet(
            new UserBoughtGameDto(
                    "d1mn",
                    "Call of duty",
                    LocalDate.of(2011, 2, 2),
                    200,
                    20
            ),
            new UserBoughtGameDto(
                    "pesh0",
                    "Call of duty 2",
                    LocalDate.of(2015, 3, 2),
                    10000,
                    30
            )
    );

    private static final Set<UserWishlistGameDto> wishlistGames = com.google.common.collect.Sets.newHashSet(
            new UserWishlistGameDto(
                    "pesh0",
                    "Call of duty 3",
                    LocalDate.of(2015, 3, 2)
            ),
            new UserWishlistGameDto(
                    "d1mn",
                    "Call of duty 2",
                    LocalDate.of(2011, 2, 2)
            )
    );

    private static final Set<UserAchievementDto> userAchievements = com.google.common.collect.Sets.newHashSet(
            new UserAchievementDto(
                    "pesh0",
                    "Call of duty 3",
                    LocalDate.of(2015, 3, 2)
            ),
            new UserAchievementDto(
                    "d1mn",
                    "Call of duty 2",
                    LocalDate.of(2011, 2, 2)
            )
    );

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

        userGetDto.getBoughtGames().addAll(boughtGames);
        userGetDto.getWishlistGames().addAll(wishlistGames);
        userGetDto.getAchievements().addAll(userAchievements);
        userGetDto.getFriends().addAll(friends);
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
    public void filledBoughtGamesSerialize() throws IOException {

        //the order of which games are shown is not the same in which they are added
        //so it is harder to test the whole json

        assertThatJson(json.write(userGetDto).getJson())
                .node("boughtGames")
                .isArray().ofLength(2);
    }

    @Test
    public void filledWishlistGamesSerialize() throws IOException {
        assertThatJson(json.write(userGetDto).getJson())
                .node("wishlistGames")
                .isArray().ofLength(2);
    }

    @Test
    public void filledAchievementsSerialize() throws IOException {
        assertThatJson(json.write(userGetDto).getJson())
                .node("achievements")
                .isArray().ofLength(2);
    }

    @Test
    public void filledFriendsSerialize() throws IOException {
        assertThatJson(json.write(userGetDto).getJson())
                .node("friends")
                .isArray().ofLength(3);
    }
}
