package gamestore.exceptions;


import org.springframework.security.core.AuthenticationException;

public class UserBirthdayNotValidException extends AuthenticationException {
    public UserBirthdayNotValidException(String msg) {
        super(msg);
    }
}
