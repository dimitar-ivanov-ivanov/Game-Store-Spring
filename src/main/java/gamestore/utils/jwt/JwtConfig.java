package gamestore.utils.jwt;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.google.common.net.HttpHeaders;
import org.springframework.stereotype.Component;

/**
 * The Jwt config.
 * Main properties are taken from application properties file
 *
 * @author Dimitar Ivanov
 */
@Getter
@Setter
@NoArgsConstructor
@Configuration
@Component
@ConfigurationProperties(prefix = "application.jwt")
public class JwtConfig {

    private String secretKey;
    private String tokenPrefix;
    private Integer tokenExpirationAfterDays;

    /**
     * Gets authorization header.
     *
     * @return the authorization header
     */
    public String getAuthorizationHeader() {
        return HttpHeaders.AUTHORIZATION;
    }
}
