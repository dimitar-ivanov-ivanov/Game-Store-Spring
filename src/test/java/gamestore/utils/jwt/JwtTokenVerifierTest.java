package gamestore.utils.jwt;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.mock.web.MockFilterChain;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JwtTokenVerifierTest {

    private static JwtTokenVerifier underTest;
    private static JwtConfig jwtConfig;

    @Mock
    private static HttpServletRequest request;

    @Mock
    private static HttpServletResponse response;

    private static FilterChain filterChain;

    @BeforeAll
    static void setUp() {
        underTest = new JwtTokenVerifier(jwtConfig);
        jwtConfig = new JwtConfig();
        filterChain = new MockFilterChain();
    }

    @Test
    void shouldReturnBadHeader()
            throws ServletException, IOException {

        when(request.getHeader
                (jwtConfig.getAuthorizationHeader()))
                .thenReturn("");

        underTest.doFilterInternal(request,
                response,
                filterChain);

        verify(filterChain, atLeastOnce())
                .doFilter(request, response);
    }
}