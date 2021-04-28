package gamestore.models.dtos;

import gamestore.utils.formatters.LocalDateFormatter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Locale;

import static net.javacrumbs.jsonunit.fluent.JsonFluentAssert.assertThatJson;

@JsonTest
class UserGameBadgeDtoTest {

    @Autowired
    private JacksonTester<UserGameBadgeDto> json;

    private static final String USERNAME = "stQn";
    private static final String GAME_NAME = "Call of duty";
    private static final String BADGE_NAME = "Noob";
    private static final String EARNED_ON = "2021-03-25";
    private static final BigDecimal price = BigDecimal.valueOf(15);

    private static UserGameBadgeDto underTest;

    private static final LocalDateFormatter localDateFormatter =
            new LocalDateFormatter();


    private static LocalDate parseDate(final String dateString) {
        try {
            return localDateFormatter.parse(dateString, Locale.ROOT);
        } catch (final ParseException e) {
            return LocalDate.now();
        }
    }

    @BeforeAll
    static void setUp() {
        underTest = new UserGameBadgeDto(
                USERNAME,
                GAME_NAME,
                BADGE_NAME,
                parseDate(EARNED_ON),
                price
        );
    }

    @Test
    void dateSerializes() throws IOException {
        assertThatJson(this.json.write(underTest).getJson())
                .node("earnedOn")
                .isEqualTo(EARNED_ON);
    }

    @Test
    void userNameSerializes() throws IOException {
        assertThatJson(json.write(underTest).getJson())
                .node("username")
                .isEqualTo(USERNAME);
    }

    @Test
    void gameNameSerializes() throws IOException {
        assertThatJson(json.write(underTest).getJson())
                .node("gameName")
                .isEqualTo(GAME_NAME);
    }

    @Test
    void badgeNameSerializes() throws IOException {
        assertThatJson(json.write(underTest).getJson())
                .node("badgeName")
                .isEqualTo(BADGE_NAME);
    }

    @Test
    void priceSerializes() throws IOException {
        assertThatJson(json.write(underTest).getJson())
                .node("price")
                .isEqualTo(price);
    }

}