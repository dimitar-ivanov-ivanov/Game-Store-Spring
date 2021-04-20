package gamestore.models.bindings;

import gamestore.models.enums.Gender;
import gamestore.utils.annotations.email.Email;
import gamestore.utils.annotations.name.UserName;
import gamestore.utils.annotations.password.Password;
import gamestore.utils.constants.TextConstants;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

/**
 * The User register binding model.
 *
 * @author Dimitar Ivanov
 */
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class UserRegisterBindingModel {

    /**
     * The user name passed by the client
     *
     * @see UserName
     */
    @UserName
    private String username;

    /**
     * The email passed by the client
     *
     * @see Email
     */
    @Email
    private String email;

    /**
     * The password passed by the client
     *
     * @see Password
     */
    @Password
    private String password;

    /**
     * The first name passed by the client.
     * Must not be empty or null
     */
    @NotBlank(message = "first " + TextConstants.NAME_CANNOT_BE_BLANK)
    private String firstName;

    /**
     * The last name passed by the client.
     * Must not be empty or null
     */
    @NotBlank(message = "last " + TextConstants.NAME_CANNOT_BE_BLANK)
    private String lastName;

    /**
     * The birth date passed by the client.
     * Must not be null or in the future
     */
    @NotNull
    @Past
    private LocalDate birthDate;

    /**
     * The gender passed by the client.
     */
    private Gender gender;
}