package gamestore.models.entities.security;

import gamestore.utils.constants.TextConstants;
import gamestore.models.entities.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

/**
 * The type Role.
 *
 * @author Dimitar Ivanov
 */
@Getter
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;

    /**
     * The name of the role.
     * Must be not be empty or null.
     *
     * @see TextConstants#NAME_CANNOT_BE_BLANK
     */
    @NotBlank(message = TextConstants.NAME_CANNOT_BE_BLANK)
    private String name;

    /**
     * The users who have this role
     *
     * @see User
     */
    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
    private Set<User> users;

    /**
     * The authorities who are included in this role.
     *
     * @see Authority
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "roles_authorities",
            joinColumns = @JoinColumn(
                    name = "role_id",
                    referencedColumnName = "role_id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "authority_id",
                    referencedColumnName = "authority_id"
            )
    )
    private Set<Authority> authorities;

    /**
     * Instantiates a new Role.
     *
     * @param name the name of the role
     */
    public Role(String name) {
        this.name = name;
        this.users = new HashSet<>();
        this.authorities = new HashSet<>();
    }
}
