package gamestore.utils.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The Data out of range exception.
 *
 * @author Dimitar Ivanov
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DataOutOfRangeException extends AuthenticationException {

    /**
     * Instantiates a new Data out of range exception.
     *
     * @param msg the message
     */
    public DataOutOfRangeException(String msg) {
        super(msg);
    }
}
