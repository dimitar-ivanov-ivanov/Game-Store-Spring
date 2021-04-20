package gamestore.models.entities.user;

import gamestore.models.bindings.UserRegisterBindingModel;
import gamestore.models.entities.game.Review;
import gamestore.utils.annotations.email.Email;
import gamestore.utils.annotations.name.UserName;
import gamestore.utils.annotations.password.Password;
import gamestore.utils.constants.TextConstants;
import gamestore.models.entities.security.Authority;
import gamestore.models.entities.security.Role;
import gamestore.models.enums.Gender;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * The type User.
 *
 * @author Dimitar Ivanov
 */
@Getter
@Setter
@Entity(name = "user")
@Table(
        name = "users",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "username_unique",
                        columnNames = "username"
                ),
                @UniqueConstraint(
                        name = "email_unique",
                        columnNames = "email"
                )
        }
)
public class User implements UserDetails, Serializable {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(name = "user_id")
    private Long userId;

    /**
     * The first name of the authority.
     * Must be not be empty or null.
     *
     * @see TextConstants#NAME_CANNOT_BE_BLANK
     */
    @NotBlank(message = "first " + TextConstants.NAME_CANNOT_BE_BLANK)
    @Column(name = "first_name")
    private String firstName;

    /**
     * The last name of the authority.
     * Must be not be empty or null.
     *
     * @see TextConstants#NAME_CANNOT_BE_BLANK
     */
    @NotBlank(message = "last " + TextConstants.NAME_CANNOT_BE_BLANK)
    @Column(name = "last_name")
    private String lastName;

    @Column(
            name = "birth_date",
            nullable = false
    )
    @Past
    private LocalDate birthDate;

    /**
     * The user name of the user
     *
     * @see UserName
     */
    @UserName
    private String username;

    /**
     * The email of the user
     *
     * @see Email
     */
    @Email
    private String email;


    /**
     * The password of the user.
     * Password Annotation here is not used because when we pass the password to the user it is already encoded
     * and it doesn't satisfy the internal validation  of the Password annotation.
     */
    private String password;

    /**
     * The gender of the user.
     *
     * @see Gender
     */
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(
            name = "is_expired"
    )
    private boolean isExpired = false;
    @Column(
            name = "is_locked"
    )
    private boolean isLocked = false;
    @Column(
            name = "is_enabled"
    )
    private boolean isEnabled = true;

    /**
     * The friends of the user.
     */
    @ManyToMany
    @JoinTable(
            name = "users_friends",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id")
    )
    private Set<User> friends;

    /**
     * The games bought by the user
     *
     * @see UserBoughtGame
     */
    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.MERGE,
            orphanRemoval = true
    )
    private Set<UserBoughtGame> boughtGames;

    /**
     * The games the user wishesh to buy.
     *
     * @see UserWishlistGame
     */
    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.MERGE,
            orphanRemoval = true
    )
    private Set<UserWishlistGame> wishlistGames;

    /**
     * The game badges earned by the user
     *
     * @see UserGameBadge
     */
    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.MERGE,
            orphanRemoval = true
    )
    private Set<UserGameBadge> gameBadges;

    /**
     * the achievement earned by the user
     *
     * @see UserAchievement
     */
    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.MERGE,
            orphanRemoval = true
    )
    private Set<UserAchievement> achievements;

    /**
     * The reviews written by the user.
     *
     * @see Review
     */
    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.MERGE,
            orphanRemoval = true
    )
    private Set<Review> reviews;

    /**
     * The roles of the user
     *
     * @see Role
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id",
                    referencedColumnName = "user_id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id",
                    referencedColumnName = "role_id"
            )
    )
    private Set<Role> roles;

    /**
     * Instantiates a new User.
     */
    public User() {
        this.friends = new HashSet<>();
        this.boughtGames = new HashSet<>();
        this.wishlistGames = new HashSet<>();
        this.roles = new HashSet<>();
        this.gameBadges = new HashSet<>();
        this.achievements = new HashSet<>();
        this.reviews = new HashSet<>();
    }

    /**
     * Instantiates a new User.
     *
     * @param firstName the first name
     * @param lastName  the last name
     * @param birthDate the birth date
     * @param username  the username
     * @param email     the email
     * @param password  the password
     * @param gender    the gender
     */
    public User(String firstName,
                String lastName,
                LocalDate birthDate,
                String username,
                String email,
                String password,
                Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.username = username;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.friends = new HashSet<>();
        this.boughtGames = new HashSet<>();
        this.wishlistGames = new HashSet<>();
        this.roles = new HashSet<>();
        this.gameBadges = new HashSet<>();
        this.achievements = new HashSet<>();
        this.reviews = new HashSet<>();
    }

    /**
     * Instantiates a new User.
     *
     * @param id        the id
     * @param firstName the first name
     * @param lastName  the last name
     * @param birthDate the birth date
     * @param username  the username
     * @param email     the email
     * @param password  the password
     * @param gender    the gender
     */
    public User(Long id,
                String firstName,
                String lastName,
                LocalDate birthDate,
                String username,
                String email,
                String password,
                Gender gender) {
        this.userId = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.username = username;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.friends = new HashSet<>();
        this.boughtGames = new HashSet<>();
        this.wishlistGames = new HashSet<>();
        this.roles = new HashSet<>();
        this.gameBadges = new HashSet<>();
        this.achievements = new HashSet<>();
        this.reviews = new HashSet<>();
    }

    /**
     * Get all authorities from the roles the user has
     * Parse those authorities to SimpleGrantedAuthority and return
     * I tried to just return Authorities who extend GrantedAuthority but it didn't work
     * The jwt only recognised existing Granted Authorities.
     *
     * @return a collection of granted authorities
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Authority> authorities = new HashSet<>();
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        for (Role role : roles) {
            authorities.addAll(role.getAuthorities());
            grantedAuthorities.add(new SimpleGrantedAuthority(
                    "ROLE_" + role.getName()
            ));
        }

        grantedAuthorities.addAll(authorities
                .stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getAuthority()))
                .collect(Collectors.toSet()));

        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !isExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !isLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    /**
     * Equals binding model boolean.
     * Use it for testing -> UserServiceTest#shouldRegisterUser()
     *
     * @param model the binding model
     * @return the boolean indicating whether they share the same data
     */
    public boolean equalsBindingModel(UserRegisterBindingModel model) {
        return this.username.equals(model.getUsername()) &&
                this.email.equals(model.getEmail());
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", isExpired=" + isExpired +
                ", isLocked=" + isLocked +
                ", isEnabled=" + isEnabled +
                '}';
    }
}
