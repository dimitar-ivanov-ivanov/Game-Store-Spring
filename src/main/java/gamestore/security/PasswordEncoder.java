package gamestore.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * The Password encoder.
 *
 * @author Dimitar Ivanov
 */
@Configuration
public class PasswordEncoder {

    /**
     * B crypt password encoder
     *
     * @return the b crypt password encoder
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
}
