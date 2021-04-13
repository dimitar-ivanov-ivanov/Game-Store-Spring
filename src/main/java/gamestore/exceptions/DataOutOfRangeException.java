package gamestore.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DataOutOfRangeException extends AuthenticationException {

    public DataOutOfRangeException(String msg) {
        super(msg);
    }
}
