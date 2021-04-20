package gamestore.utils.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

/**
 * The type Jwt username and password authentication filter.
 *
 * @author Dimitar Ivanov
 */
public class JwtUsernameAndPasswordAuthenticationFilter extends
        UsernamePasswordAuthenticationFilter {

    private final JwtConfig jwtConfig;
    private final AuthenticationManager authenticationManager;

    /**
     * Instantiates a new Jwt username and password authentication filter.
     *
     * @param jwtConfig             the jwt config
     * @param authenticationManager the authentication manager
     */
    @Autowired
    public JwtUsernameAndPasswordAuthenticationFilter(JwtConfig jwtConfig,
                                                      AuthenticationManager authenticationManager) {
        this.jwtConfig = jwtConfig;
        this.authenticationManager = authenticationManager;
    }

    /**
     * Client sends credentials to server
     * The server authenticates the credentials
     *
     * @param request
     * @param response
     * @return Authentication
     * @throws AuthenticationException
     */
    @Override
    public Authentication attemptAuthentication
    (HttpServletRequest request,
     HttpServletResponse response)
            throws AuthenticationException {

        try {
            UsernameAndPasswordAuthenticationRequest authenticationRequest =
                    new ObjectMapper()
                            .readValue(request.getInputStream(),
                                    UsernameAndPasswordAuthenticationRequest.class);

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(),
                    authenticationRequest.getPassword()
            );

            Authentication authenticate = authenticationManager
                    .authenticate(authentication);

            return authenticate;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication
            (HttpServletRequest request,
             HttpServletResponse response,
             FilterChain chain,
             Authentication authResult)
            throws IOException, ServletException {

        //generate token
        String token = Jwts.builder()
                .setSubject(authResult.getName())
                .claim("authorities", authResult.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(
                        java.sql.Date.valueOf(
                                LocalDate.now()
                                        .plusDays(
                                                jwtConfig.getTokenExpirationAfterDays()
                                        )
                        )
                )
                .signWith(SignatureAlgorithm.HS256, jwtConfig.getSecretKey())
                .compact();

        //send token
        response.addHeader(jwtConfig.getAuthorizationHeader(),
                jwtConfig.getTokenPrefix() + token);
    }
}
