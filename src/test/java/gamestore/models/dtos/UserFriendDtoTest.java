package gamestore.models.dtos;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;

import static net.javacrumbs.jsonunit.fluent.JsonFluentAssert.assertThatJson;

@JsonTest
class UserFriendDtoTest {

    @Autowired
    private JacksonTester<UserFriendDto> json;

    private static final String USERNAME = "stQn";

    private static UserFriendDto underTest;

    @BeforeAll
    static void setUp() {
        underTest = new UserFriendDto(USERNAME);
    }

    @Test
    void usernameSerializes() throws IOException {
        assertThatJson(this.json.write(underTest).getJson())
                .node("username")
                .isEqualTo(USERNAME);
    }

}