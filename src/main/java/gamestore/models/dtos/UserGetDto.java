package gamestore.models.dtos;

import gamestore.models.entities.user.*;
import gamestore.models.enums.Gender;
import gamestore.utils.annotations.email.Email;
import gamestore.utils.annotations.name.UserName;
import gamestore.utils.constants.TextConstants;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.Set;

/**
 * The User get dto.
 */
@Data
@EqualsAndHashCode
public class UserGetDto {

    private String firstName;

    private String lastName;

    private LocalDate birthDate;

    private String username;

    private String email;

    private Gender gender;

    /*
      -> get just their name and basic details
    private Set<User> friends;
    */

    private Set<UserBoughtGame> boughtGames;

    private Set<UserWishlistGame> wishlistGames;

    private Set<UserGameBadge> gameBadges;

    private Set<UserAchievement> achievements;
}
