package gamestore.utils.exceptions.user;


import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The User birthday not valid exception.
 *
 * @author Dimitar Ivanov
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserBirthdayNotValidException extends AuthenticationException {
    /**
     * Instantiates a new User birthday not valid exception.
     *
     * @param msg the message
     */
    public UserBirthdayNotValidException(String msg) {
        super(msg);
    }
}
