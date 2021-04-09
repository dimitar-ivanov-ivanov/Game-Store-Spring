package gamestore.exceptions;


import org.springframework.security.core.AuthenticationException;

public class DataOutOfRangeException extends AuthenticationException {

    public DataOutOfRangeException(String msg) {
        super(msg);
    }
}
