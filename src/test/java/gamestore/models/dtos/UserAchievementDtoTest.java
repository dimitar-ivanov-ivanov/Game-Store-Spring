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
class UserAchievementDtoTest {

    @Autowired
    private JacksonTester<UserAchievementDto> json;

    private static final String USERNAME = "stQn";
    private static final String ACHIEVEMENT_NAME = "Kill 100 enemies.";
    private static final String EARNED_ON = "2021-03-25";

    private static UserAchievementDto userAchievementDto;

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
        userAchievementDto = new UserAchievementDto(
                USERNAME,
                ACHIEVEMENT_NAME,
                parseDate(EARNED_ON)
        );
    }

    @Test
    public void dateSerializes() throws IOException {
        assertThatJson(this.json.write(userAchievementDto).getJson())
                .node("earnedOn")
                .isEqualTo(EARNED_ON);
    }

    @Test
    public void userNameSerializes() throws IOException {
        assertThatJson(json.write(userAchievementDto).getJson())
                .node("username")
                .isEqualTo(USERNAME);
    }

    @Test
    public void gameNameSerializes() throws IOException {
        assertThatJson(json.write(userAchievementDto).getJson())
                .node("achievementName")
                .isEqualTo(ACHIEVEMENT_NAME);
    }
}