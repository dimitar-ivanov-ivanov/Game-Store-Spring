package gamestore.models.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import gamestore.models.enums.Gender;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * The User get dto.
 * Intended to be passed to the client
 *
 * @author Dimitar Ivanov
 */
@Data
@EqualsAndHashCode
public class UserGetDto {

    private String firstName;

    private String lastName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    private String username;

    private String email;

    private Gender gender;

    /*
      -> get just their name and basic details
    private Set<User> friends;
    */

    private Set<UserBoughtGameDto> boughtGames;

    private Set<UserWishlistGameDto> wishlistGames;

    private Set<UserGameBadgeDto> gameBadges;

    private Set<UserAchievementDto> achievements;

    public UserGetDto(String firstName,
                      String lastName,
                      LocalDate birthDate,
                      String username,
                      String email,
                      Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.username = username;
        this.email = email;
        this.gender = gender;
        this.boughtGames = new HashSet<>();
        this.wishlistGames = new HashSet<>();
        this.gameBadges = new HashSet<>();
        this.achievements = new HashSet<>();
    }
}
