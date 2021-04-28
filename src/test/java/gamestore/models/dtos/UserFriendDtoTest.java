package gamestore.models.dtos;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;

import static net.javacrumbs.jsonunit.fluent.JsonFluentAssert.assertThatJson;
import static org.junit.jupiter.api.Assertions.*;

@JsonTest
class UserFriendDtoTest {

    @Autowired
    private JacksonTester<UserFriendDto> json;

    private static final String USERNAME = "stQn";

    private static UserFriendDto userFriendDto;

    @BeforeAll
    public static void setUp() {
        userFriendDto = new UserFriendDto(USERNAME);
    }

    @Test
    public void usernameSerializes() throws IOException {
        assertThatJson(this.json.write(userFriendDto).getJson())
                .node("username")
                .isEqualTo(USERNAME);
    }

}