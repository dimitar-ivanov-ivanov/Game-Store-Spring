package gamestore.exceptions;

public class InvalidOrExpiredJwtToken extends RuntimeException {

    public InvalidOrExpiredJwtToken(String message) {
        super(message);
    }
}
