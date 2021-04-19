package gamestore.utils.jwt;

import gamestore.utils.constants.TextConstants;
import gamestore.exceptions.InvalidOrExpiredJwtTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class JwtTokenVerifier extends OncePerRequestFilter {

    private final JwtConfig jwtConfig;

    @Autowired
    public JwtTokenVerifier(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    /*
       This filter is invoker once per request from the client.
       Get token from the header
    */
    @Override
    protected void doFilterInternal
    (HttpServletRequest request,
     HttpServletResponse response,
     FilterChain filterChain)
            throws ServletException, IOException {

        String authorizationHeader = request.getHeader
                (jwtConfig.getAuthorizationHeader());

        /*
           Bad header, don't authenticate
         */
        if (Strings.isEmpty(authorizationHeader) ||
                !authorizationHeader.startsWith(jwtConfig.getTokenPrefix())) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authorizationHeader
                .replace(jwtConfig.getTokenPrefix(), "");

        try {

            /*
              Use jws because we compacted the header when sending
             */
            Jws<Claims> claims = Jwts.parser()
                    .setSigningKey(jwtConfig.getSecretKey())
                    .parseClaimsJws(token);

            Claims body = claims.getBody();
            String username = body.getSubject();

            List<Map<String, String>> authorities = (List<Map<String, String>>) body.get("authorities");

            Set<SimpleGrantedAuthority> simpleGrantedAuthorities = authorities
                    .stream()
                    .map(m -> new SimpleGrantedAuthority(m.get("authority")))
                    .collect(Collectors.toSet());

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    username,
                    null,
                    simpleGrantedAuthorities
            );

            SecurityContextHolder.getContext()
                    .setAuthentication(authentication);
        } catch (JwtException e) {
            /*
              Thrown when invalid or expired token
             */
            throw new InvalidOrExpiredJwtTokenException(
                    String.format(TextConstants.TOKEN_CANNOT_BE_TRUSTED, token)
            );
        }

        /*
          Each filter must pass the request and response to the next filter
         */
        filterChain.doFilter(request, response);
    }
}