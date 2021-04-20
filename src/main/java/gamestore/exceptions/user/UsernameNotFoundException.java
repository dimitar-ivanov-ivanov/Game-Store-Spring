package gamestore.exceptions.user;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The Username not found exception.
 *
 * @author Dimitar Ivanov
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class UsernameNotFoundException extends AuthenticationException {

    /**
     * Instantiates a new Username not found exception.
     *
     * @param message the message
     */
    public UsernameNotFoundException(String message) {
        super(message);
    }
}
