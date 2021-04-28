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
import static org.junit.jupiter.api.Assertions.*;

@JsonTest
class UserWishlistGameDtoTest {

    @Autowired
    private JacksonTester<UserWishlistGameDto> json;

    private static final String USERNAME = "stQn";
    private static final String GAME_NAME = "Call of duty.";
    private static final String ADDED_ON = "2021-03-25";

    private static UserWishlistGameDto userWishlistGameDto;

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
        userWishlistGameDto = new UserWishlistGameDto(
                USERNAME,
                GAME_NAME,
                parseDate(ADDED_ON)
        );
    }

    @Test
    public void dateSerializes() throws IOException {
        assertThatJson(this.json.write(userWishlistGameDto).getJson())
                .node("addedOn")
                .isEqualTo(ADDED_ON);
    }

    @Test
    public void userNameSerializes() throws IOException {
        assertThatJson(json.write(userWishlistGameDto).getJson())
                .node("username")
                .isEqualTo(USERNAME);
    }

    @Test
    public void gameNameSerializes() throws IOException {
        assertThatJson(json.write(userWishlistGameDto).getJson())
                .node("gameName")
                .isEqualTo(GAME_NAME);
    }
}