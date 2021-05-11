package gamestore.controllers;

import gamestore.models.dtos.UserGetDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerIntegrationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    @Sql("/test.sql")
    void getUserByIdTest() {
        //UserGetDto response = testRestTemplate.getForObject("/user/1001", UserGetDto.class);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization",
                "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJkMW1OIiwiYXV0aG9yaXRpZXMiOlt7ImF1dGhvcml0eSI6ImdhbWU6ZGVsZXRlIn0seyJhdXRob3JpdHkiOiJ1c2VyOmRlbGV0ZSJ9LHsiYXV0aG9yaXR5IjoidXNlcjpyZWFkIn0seyJhdXRob3JpdHkiOiJST0xFX0FETUlOIn0seyJhdXRob3JpdHkiOiJ1c2VyOnVwZGF0ZSJ9LHsiYXV0aG9yaXR5IjoiZ2FtZTp1cGRhdGUifSx7ImF1dGhvcml0eSI6ImdhbWU6d3JpdGUifSx7ImF1dGhvcml0eSI6ImdhbWU6cmVhZCJ9XSwiaWF0IjoxNjIwNjIxMzg0LCJleHAiOjE2MjE4MDM2MDB9.I9SZnNEJyrJQex41XKnolM65IY5dSxDYrE7W1PNR2yg");
        ResponseEntity<UserGetDto> dto = testRestTemplate.exchange
                ("http://localhost:8086/user?userId=1001", HttpMethod.GET, new HttpEntity<>(headers), UserGetDto.class);

        System.out.println("мхм");
        /*
        assertThat(response.getFirstName())
                .isEqualTo("dimitar");

        assertThat(response.getLastName())
                .isEqualTo("pekotv");

        assertThat(response.getUsername())
                .isEqualTo("petkoptko123da");
         */
    }
}
