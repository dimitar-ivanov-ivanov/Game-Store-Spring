package gamestore.exceptions;


import org.springframework.security.core.AuthenticationException;

public class InvalidOrExpiredJwtTokenException
        extends AuthenticationException {

    public InvalidOrExpiredJwtTokenException(String message) {
        super(message);
    }
}
