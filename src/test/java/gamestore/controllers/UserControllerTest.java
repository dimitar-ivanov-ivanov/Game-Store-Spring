package gamestore.controllers;

import gamestore.data.services.UserService;
import gamestore.models.bindings.UserRegisterBindingModel;
import gamestore.models.dtos.UserGetDto;
import gamestore.models.entities.user.User;
import gamestore.models.enums.Gender;
import gamestore.security.CustomAuthenticator;
import gamestore.utils.constants.TextConstants;
import gamestore.utils.exceptions.user.UserNotFoundException;
import gamestore.utils.jwt.JwtConfig;
import gamestore.utils.jwt.JwtTokenVerifier;
import gamestore.utils.jwt.JwtUsernameAndPasswordAuthenticationFilter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.NestedServletException;

import java.time.LocalDate;
import java.util.concurrent.CompletableFuture;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@WebAppConfiguration
@AutoConfigureJsonTesters
class UserControllerTest {

    private final String USERNAME = "niKolaaa";
    private final String PASSWORD = "A_35aa51A";
    private final String EMAIL = "nikola@abv.bg";
    private final String FIRST_NAME = "nikola";
    private final String LAST_NAME = "siderov";
    private final LocalDate BIRTH_DATE = LocalDate.of(1980, 5, 3);

    private final String TOKEN = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJkMW1OIiwiYXV0aG9yaXRpZXMiOlt7ImF1dGhvcml0eSI6ImdhbWU6ZGVsZXRlIn0seyJhdXRob3JpdHkiOiJ1c2VyOmRlbGV0ZSJ9LHsiYXV0aG9yaXR5IjoidXNlcjpyZWFkIn0seyJhdXRob3JpdHkiOiJST0xFX0FETUlOIn0seyJhdXRob3JpdHkiOiJnYW1lOnVwZGF0ZSJ9LHsiYXV0aG9yaXR5IjoidXNlcjp1cGRhdGUifSx7ImF1dGhvcml0eSI6ImdhbWU6cmVhZCJ9LHsiYXV0aG9yaXR5IjoiZ2FtZTp3cml0ZSJ9XSwiaWF0IjoxNjIwODg0MDYzLCJleHAiOjE2MjIwNjI4MDB9.SLGDvnYGnnIC-yc2Rl-qVEdfUt9DlwIlSunyU6gTlq8";


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
    private JacksonTester<UserGetDto> jsonUserGetDto;

    @Autowired
    private JacksonTester<UserRegisterBindingModel> jsonBindingModel;

    @Autowired
    private FilterChainProxy springSecurityFilterChain;

    private User user = new User(
            FIRST_NAME,
            LAST_NAME,
            BIRTH_DATE,
            USERNAME,
            EMAIL,
            PASSWORD,
            Gender.MALE
    );

    private CompletableFuture<User> future = CompletableFuture.completedFuture(user);

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .addFilter(springSecurityFilterChain)
                .build();
    }

    @Test
    void getUserShouldReturnOkResponseWithDto() throws Exception {
        //given
        UserGetDto dto = mapper.map(user, UserGetDto.class);

        //when
        when(userService.getById(any()))
                .thenReturn(future);

        mvc.perform(
                MockMvcRequestBuilders.get("/user")
                        .contentType(APPLICATION_JSON)
                        .param("userId", "1")
                        .header("Authorization", TOKEN)
        )
                //then
                .andExpect(status().isOk())
                .andExpect(result -> assertThat(result.getResponse().getContentType())
                        .isEqualTo("application/json"))
                .andExpect(result -> assertThat(result.getResponse().getContentAsString())
                        .isEqualTo(jsonUserGetDto.write(dto).getJson()))
                .andDo(print());
    }

    @Test
    void getUserWithNoAuthorizationShouldReturnForbidden() throws Exception {
        //given
        //when
        mvc.perform(
                MockMvcRequestBuilders.get("/user")
                        .param("userId", "1")
                        .contentType(APPLICATION_JSON)
        )
                //then
                .andExpect(status().isForbidden())
                .andDo(print());
    }

    @Test
    void getUserShouldReturnNotFoundResponse() throws Exception {
        //given
        //when
        when(userService.getById(any())).
                thenThrow(new UserNotFoundException(TextConstants.USER_NOT_FOUND));

        mvc.perform(
                MockMvcRequestBuilders.get("/user")
                        .param("userId", "90")
                        .header("Authorization", TOKEN)
        )
                .andExpect(status().isNotFound());
    }

    @Test
    void getUserWithNoRequestParamShouldReturnBadRequest() throws Exception {
        //given
        //when
        mvc.perform(
                MockMvcRequestBuilders.get("/user")
                        .header("Authorization", TOKEN)
        )
                //then
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    void registerNewUserShouldReturnIsCreatedResponseWithDto() throws Exception {
        //given
        UserRegisterBindingModel model = mapper.map(user, UserRegisterBindingModel.class);
        model.setMatchingPassword(user.getPassword());
        String body = jsonBindingModel.write(model).getJson();
        UserGetDto dto = mapper.map(user, UserGetDto.class);

        //when
        when(userService.registerUser(model))
                .thenReturn(user);

        mvc.perform(
                MockMvcRequestBuilders.post("/user/register")
                        .contentType(APPLICATION_JSON)
                        .content(body)
                        .accept(APPLICATION_JSON)
        )
                .andDo(print())
                //then
                .andExpect(status().isCreated())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(result -> assertThat(result.getResponse().getContentAsString())
                        .isEqualTo(jsonUserGetDto.write(dto).getJson()));
    }

    @Test
    void registerUserWithMissingBodyShouldReturnBadRequest() throws Exception {
        //given
        //when
        mvc.perform(
                MockMvcRequestBuilders.post("/user/register")
                        .contentType(APPLICATION_JSON)
                        .accept(APPLICATION_JSON)
        )
                .andDo(print())
                //then
                .andExpect(status().isBadRequest());
    }

    @Test
    void registerUserWithMissingColumnInBodyShouldReturnBadRequest() throws Exception {
        //given
        UserRegisterBindingModel model = mapper.map(user, UserRegisterBindingModel.class);
        String body = jsonBindingModel.write(model).getJson();

        //when
        mvc.perform(
                MockMvcRequestBuilders.post("/user/register")
                        .contentType(APPLICATION_JSON)
                        .content(body)
                        .accept(APPLICATION_JSON)
        )
                .andDo(print())
                //then
                .andExpect(status().isBadRequest());
    }


    @Test
    void deleteUser() {
    }

    @Test
    void updateUser() {
    }
}