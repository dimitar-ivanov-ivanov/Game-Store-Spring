package gamestore.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.NaturalIdCache;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

@Getter
@EqualsAndHashCode
@NoArgsConstructor
@Entity(name = "User")
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
@org.hibernate.annotations.Cache(
        usage = CacheConcurrencyStrategy.READ_WRITE
)
@NaturalIdCache
public class User implements UserDetails, Serializable {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(name = "user_id")
    private Long userId;

    @Column(
            name = "first_name",
            nullable = false
    )
    private String firstName;

    @Column(
            name = "last_name",
            nullable = false
    )
    private String lastName;

    @Column(
            name = "birth_date",
            nullable = false
    )
    private LocalDate birthDate;

    @Transient
    private Integer age;

    @NaturalId
    private String username;
    private String email;
    private String password;

    @Column(
            name = "is_expired"
    )
    private boolean isExpired;
    @Column(
            name = "is_locked"
    )
    private boolean isLocked;
    @Column(
            name = "is_enabled"
    )
    private boolean isEnabled;

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

    @ManyToMany
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

    public User(String firstName,
                String lastName,
                LocalDate birthDate,
                String username,
                String email,
                String password,
                boolean isExpired,
                boolean isLocked,
                boolean isEnabled) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.username = username;
        this.email = email;
        this.password = password;
        this.age = Period.between(birthDate, LocalDate.now()).getYears();
        this.isExpired = isExpired;
        this.isLocked = isLocked;
        this.isEnabled = isEnabled;
        this.friends = new HashSet<>();
        this.boughtGames = new HashSet<>();
        this.wishlistGames = new HashSet<>();
        this.roles = new HashSet<>();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Authority> authorities = new HashSet<>();
        for (Role role : roles) {
            authorities.addAll(role.getAuthorities());
        }
        return authorities;
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
        return !isEnabled;
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
