package gamestore.models.dtos;

import gamestore.utils.formatters.LocalDateFormatter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Locale;

import static net.javacrumbs.jsonunit.fluent.JsonFluentAssert.assertThatJson;

@JsonTest
class UserBoughtGameDtoTest {

    @Autowired
    private JacksonTester<UserBoughtGameDto> json;

    private static final String USERNAME = "stQn";
    private static final String GAME_NAME = "Call of duty";
    private static final String BOUGHT_ON = "2021-03-25";
    private static final int HOURS_TOTAL_PLAYED = 100;
    private static final int HOURS_PLAYED_LAST_TWO_WEEKS = 5;

    private static UserBoughtGameDto underTest;

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
        underTest = new UserBoughtGameDto(
                USERNAME,
                GAME_NAME,
                parseDate(BOUGHT_ON),
                HOURS_TOTAL_PLAYED,
                HOURS_PLAYED_LAST_TWO_WEEKS
        );
    }

    @Test
    void dateSerializes() throws IOException {
        assertThatJson(this.json.write(underTest).getJson())
                .node("boughtOn")
                .isEqualTo(BOUGHT_ON);
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
    void hoursTotalPlayedSerializes() throws IOException {
        assertThatJson(json.write(underTest).getJson())
                .node("hoursPlayedTotal")
                .isEqualTo(HOURS_TOTAL_PLAYED);
    }

    @Test
    void hoursPlayedLastTwoWeeksSerializes() throws IOException {
        assertThatJson(json.write(underTest).getJson())
                .node("hoursPlayerLastTwoWeeks")
                .isEqualTo(HOURS_PLAYED_LAST_TWO_WEEKS);
    }
}