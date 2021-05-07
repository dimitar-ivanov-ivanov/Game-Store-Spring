package gamestore.controllers;

import gamestore.models.dtos.UserGetDto;
import gamestore.models.entities.user.User;
import gamestore.models.enums.Gender;
import gamestore.services.UserService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    private final String USERNAME_VALID = "niKolaaa";
    private final String EMAIL_VALID = "nikola@abv.bg";
    private final String PASSWORD_VALID = "A5a381bcacA";
    private final String FIRST_NAME_VALID = "nikola";
    private final String LAST_NAME_VALID = "siderov";
    private final LocalDate BIRTH_DATE_VALID = LocalDate.of(1980, 5, 3);

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @Mock
    private ModelMapper mapper;

    @Test
    void getUser() throws Exception {
        User user = new User(
                "Dimitar",
                "Ivanov",
                LocalDate.of(1999, 12, 12),
                "d1Mn",
                "dimitar.ivanov@abv.bg",
                "A_BCD53abz",
                Gender.MALE
        );

        UserGetDto dto = new UserGetDto();
        when(userService.getById(any()))
                .thenReturn(user);

        when(mapper.map(user, UserGetDto.class))
                .thenReturn(dto);

        MvcResult result = mockMvc.perform(get("/user/")
                .param("userId", "1")
                .header("Authorization",
                        "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJkMW1OIiwiYXV0aG9yaXRpZXMiOlt7ImF1dGhvcml0eSI6ImdhbWU6ZGVsZXRlIn0seyJhdXRob3JpdHkiOiJ1c2VyOmRlbGV0ZSJ9LHsiYXV0aG9yaXR5IjoidXNlcjpyZWFkIn0seyJhdXRob3JpdHkiOiJST0xFX0FETUlOIn0seyJhdXRob3JpdHkiOiJnYW1lOnVwZGF0ZSJ9LHsiYXV0aG9yaXR5IjoidXNlcjp1cGRhdGUifSx7ImF1dGhvcml0eSI6ImdhbWU6cmVhZCJ9LHsiYXV0aG9yaXR5IjoiZ2FtZTp3cml0ZSJ9XSwiaWF0IjoxNjIwMzg3MDk5LCJleHAiOjE2MjE1NDQ0MDB9.9Pr29BQoikuGJ2BcmEabWs5t0ASqDHlXCmqW3sDQQ7A"))
                .andExpect(status().isOk())
                .andReturn();

        assertThat(result)
                .isEqualTo(dto);
    }

    @Test
    void registerNewUser() {
    }

    @Test
    void deleteUser() {
    }

    @Test
    void updateUser() {
    }
}