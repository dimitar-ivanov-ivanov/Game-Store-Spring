package gamestore.exceptions.user;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The User not found exception.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends AuthenticationException {

    /**
     * Instantiates a new User not found exception.
     *
     * @param message the message
     */
    public UserNotFoundException(String message) {
        super(message);
    }
}
