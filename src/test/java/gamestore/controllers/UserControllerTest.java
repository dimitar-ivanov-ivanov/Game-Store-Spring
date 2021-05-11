package gamestore.controllers;

import gamestore.models.dtos.UserGetDto;
import gamestore.models.entities.user.User;
import gamestore.models.enums.Gender;
import gamestore.security.CustomAuthenticator;
import gamestore.data.services.UserService;
import gamestore.utils.constants.TextConstants;
import gamestore.utils.exceptions.user.UserNotFoundException;
import gamestore.utils.jwt.JwtConfig;
import gamestore.utils.jwt.JwtTokenVerifier;
import gamestore.utils.jwt.JwtUsernameAndPasswordAuthenticationFilter;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MvcResult;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@WebAppConfiguration
@AutoConfigureJsonTesters
class UserControllerTest {

    private final String USERNAME = "niKolaaa";
    private final String EMAIL = "nikola@abv.bg";
    private final String FIRST_NAME = "nikola";
    private final String LAST_NAME = "siderov";
    private final LocalDate BIRTH_DATE = LocalDate.of(1980, 5, 3);

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private UserService userService;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private JwtConfig jwtConfig;

    @Autowired
    private JacksonTester<UserGetDto> json;

    private User user = new User(
            FIRST_NAME,
            LAST_NAME,
            BIRTH_DATE,
            USERNAME,
            EMAIL,
            "",
            Gender.MALE
    );

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .addFilters(new JwtUsernameAndPasswordAuthenticationFilter(
                                jwtConfig, new CustomAuthenticator()
                        ),
                        new JwtTokenVerifier(jwtConfig)
                )
                .build();
    }

    @Test
    void getUserShouldReturnOkResponseWithDto() throws Exception {
        //given
        UserGetDto dto = mapper.map(user, UserGetDto.class);

        //when
        when(userService.getById(any()))
                .thenReturn(user);

        MvcResult mvcResult = mvc.perform(
                MockMvcRequestBuilders.get("/user?userId=1")
                        .param("userId", "1")
                        .header("Authorization",
                                "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJkMW1OIiwiYXV0aG9yaXRpZXMiOlt7ImF1dGhvcml0eSI6ImdhbWU6ZGVsZXRlIn0seyJhdXRob3JpdHkiOiJ1c2VyOmRlbGV0ZSJ9LHsiYXV0aG9yaXR5IjoidXNlcjpyZWFkIn0seyJhdXRob3JpdHkiOiJST0xFX0FETUlOIn0seyJhdXRob3JpdHkiOiJnYW1lOnVwZGF0ZSJ9LHsiYXV0aG9yaXR5IjoidXNlcjp1cGRhdGUifSx7ImF1dGhvcml0eSI6ImdhbWU6cmVhZCJ9LHsiYXV0aG9yaXR5IjoiZ2FtZTp3cml0ZSJ9XSwiaWF0IjoxNjE5Njc2MzAzLCJleHAiOjE2MjA4NTMyMDB9.9uUHRuS8Gbo8V6HvkigddC6Q7H8hfjiwjWM6j731uNo")
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        //then
        assertThat(mvcResult.getResponse().getStatus())
                .isEqualTo(200);

        assertThat(mvcResult.getResponse().getContentType())
                .isEqualTo("application/json");

        assertThat(mvcResult.getResponse().getContentAsString())
                .isEqualTo(json.write(dto).getJson());
    }

    @Test
    void getUserShouldReturnNotFoundResponse() throws Exception {

        //given
        //when
        when(userService.getById(any())).
                thenThrow(new UserNotFoundException(TextConstants.USER_NOT_FOUND));

        MvcResult mvcResult = mvc.perform(
                MockMvcRequestBuilders.get("/user?userId=1")
                        .param("userId", "90")
                        .header("Authorization",
                                "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJkMW1OIiwiYXV0aG9yaXRpZXMiOlt7ImF1dGhvcml0eSI6ImdhbWU6ZGVsZXRlIn0seyJhdXRob3JpdHkiOiJ1c2VyOmRlbGV0ZSJ9LHsiYXV0aG9yaXR5IjoidXNlcjpyZWFkIn0seyJhdXRob3JpdHkiOiJST0xFX0FETUlOIn0seyJhdXRob3JpdHkiOiJnYW1lOnVwZGF0ZSJ9LHsiYXV0aG9yaXR5IjoidXNlcjp1cGRhdGUifSx7ImF1dGhvcml0eSI6ImdhbWU6cmVhZCJ9LHsiYXV0aG9yaXR5IjoiZ2FtZTp3cml0ZSJ9XSwiaWF0IjoxNjE5Njc2MzAzLCJleHAiOjE2MjA4NTMyMDB9.9uUHRuS8Gbo8V6HvkigddC6Q7H8hfjiwjWM6j731uNo")
        )
                .andExpect(status().isNotFound())
                .andReturn();

        //then
        assertThat(mvcResult.getResponse().getStatus())
                .isEqualTo(404);
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