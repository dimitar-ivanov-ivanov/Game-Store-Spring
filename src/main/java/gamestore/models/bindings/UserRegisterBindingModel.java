package gamestore.models.bindings;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class UserRegisterBindingModel {

    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private int birthYear;
    private int birthMonth;
    private int birthDay;

    public LocalDate getDate() {
        return LocalDate.of(
                birthYear,
                birthMonth,
                birthDay
        );
    }
}