package gamestore.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The Invalid or expired jwt token exception.
 *
 * @author Dimitar Ivanov
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidOrExpiredJwtTokenException
        extends AuthenticationException {

    /**
     * Instantiates a new Invalid or expired jwt token exception.
     *
     * @param message the message
     */
    public InvalidOrExpiredJwtTokenException(String message) {
        super(message);
    }
}
