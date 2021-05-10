package gamestore.utils.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The Role not found exception.
 *
 * @author Dimitar Ivanov
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class RoleNotFoundException extends AuthenticationException {
    /**
     * Instantiates a new Role not found exception.
     *
     * @param msg the message
     */
    public RoleNotFoundException(String msg) {
        super(msg);
    }
}
