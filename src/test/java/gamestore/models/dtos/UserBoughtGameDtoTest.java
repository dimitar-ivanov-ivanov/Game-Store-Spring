package gamestore.models.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import gamestore.utils.formatters.LocalDateFormatter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Locale;

import static net.javacrumbs.jsonunit.fluent.JsonFluentAssert.assertThatJson;
import static org.junit.jupiter.api.Assertions.*;

@JsonTest
class UserBoughtGameDtoTest {

    @Autowired
    private JacksonTester<UserBoughtGameDto> json;

    private static final String USERNAME = "stQn";
    private static final String GAME_NAME = "Call of duty.";
    private static final String BOUGHT_ON = "2021-03-25";
    private static final int HOURS_TOTAL_PLAYED = 100;
    private static final int HOURS_PLAYED_LAST_TWO_WEEKS = 5;

    private static UserBoughtGameDto userBoughtGameDto;

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
    public static void setUp() {
        userBoughtGameDto = new UserBoughtGameDto(
                USERNAME,
                GAME_NAME,
                parseDate(BOUGHT_ON),
                HOURS_TOTAL_PLAYED,
                HOURS_PLAYED_LAST_TWO_WEEKS
        );
    }

    @Test
    public void dateSerializes() throws IOException {
        assertThatJson(this.json.write(userBoughtGameDto).getJson())
                .node("boughtOn")
                .isEqualTo(BOUGHT_ON);
    }

    @Test
    public void userNameSerializes() throws IOException {
        assertThatJson(json.write(userBoughtGameDto).getJson())
                .node("username")
                .isEqualTo(USERNAME);
    }

    @Test
    public void gameNameSerializes() throws IOException {
        assertThatJson(json.write(userBoughtGameDto).getJson())
                .node("gameName")
                .isEqualTo(GAME_NAME);
    }

    @Test
    public void hoursTotalPlayedSerializes() throws IOException {
        assertThatJson(json.write(userBoughtGameDto).getJson())
                .node("hoursPlayedTotal")
                .isEqualTo(HOURS_TOTAL_PLAYED);
    }

    @Test
    public void hoursPlayedLastTwoWeeksSerializes() throws IOException {
        assertThatJson(json.write(userBoughtGameDto).getJson())
                .node("hoursPlayerLastTwoWeeks")
                .isEqualTo(HOURS_PLAYED_LAST_TWO_WEEKS);
    }
}