package gamestore.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RoleNotFoundException extends AuthenticationException {
    public RoleNotFoundException(String msg) {
        super(msg);
    }
}
