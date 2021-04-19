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

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class UserRegisterBindingModel {

    @UserName
    private String username;
    @Email
    private String email;
    @Password
    private String password;

    @NotBlank(message = "first " + TextConstants.NAME_CANNOT_BE_BLANK)
    private String firstName;

    @NotBlank(message = "last " + TextConstants.NAME_CANNOT_BE_BLANK)
    private String lastName;

    @NotNull
    @Past
    private LocalDate birthDate;

    private Gender gender;
}