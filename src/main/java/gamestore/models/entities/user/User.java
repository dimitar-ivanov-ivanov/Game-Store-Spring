package gamestore.models.entities.user;

import gamestore.utils.annotations.email.Email;
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
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
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

    @NotBlank(message = "first " + TextConstants.NAME_CANNOT_BE_BLANK)
    @Column(name = "first_name")
    private String firstName;

    @NotBlank(message = "last " + TextConstants.NAME_CANNOT_BE_BLANK)
    @Column(name = "last_name")
    private String lastName;

    @Column(
            name = "birth_date",
            nullable = false
    )
    private LocalDate birthDate;

    @Transient
    private Integer age;

    @NotBlank(message = "user" + TextConstants.NAME_CANNOT_BE_BLANK)
    //make special annotation for validation
    private String username;

    @Email
    private String email;

    @Password
    private String password;

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

    @ManyToMany
    @JoinTable(
            name = "users_friends",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id")
    )
    private Set<User> friends;

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.MERGE,
            orphanRemoval = true
    )
    private Set<UserBoughtGame> boughtGames;

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.MERGE,
            orphanRemoval = true
    )
    private Set<UserWishlistGame> wishlistGames;

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.MERGE,
            orphanRemoval = true
    )
    private Set<UserGameBadge> gameBadges;

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.MERGE,
            orphanRemoval = true
    )
    private Set<UserAchievement> achievements;

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

    @Enumerated(EnumType.STRING)
    private Gender gender;

    public User(String firstName,
                String lastName,
                LocalDate birthDate,
                String username,
                String email,
                String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.username = username;
        this.email = email;
        this.password = password;
        this.age = Period.between(birthDate, LocalDate.now()).getYears();
        this.friends = new HashSet<>();
        this.boughtGames = new HashSet<>();
        this.wishlistGames = new HashSet<>();
        this.roles = new HashSet<>();
        this.gameBadges = new HashSet<>();
        this.achievements = new HashSet<>();
    }

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

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", age=" + age +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", isExpired=" + isExpired +
                ", isLocked=" + isLocked +
                ", isEnabled=" + isEnabled +
                '}';
    }
}
