package gamestore.utils.jwt;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The Username and password authentication request.
 *
 * @author Dimitar Ivanov
 */
@Getter
@Setter
@NoArgsConstructor
public class UsernameAndPasswordAuthenticationRequest {

    private String username;
    private String password;
}
