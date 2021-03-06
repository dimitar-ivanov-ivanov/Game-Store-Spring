package gamestore.models.entities.security;

import gamestore.utils.constants.TextConstants;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

/**
 * The type Authority.
 *
 * @author Dimitar Ivanov
 */
@NoArgsConstructor
@Entity
@Table(name = "authorities")
public class Authority implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "authority_id")
    private Long privilegeId;

    /**
     * The name of the authority.
     * Must be not be empty or null.
     *
     * @see TextConstants#NAME_CANNOT_BE_BLANK
     */
    @NotBlank(message = TextConstants.NAME_CANNOT_BE_BLANK)
    private String name;

    /**
     * The roles which include this authority.
     *
     * @see Role
     */
    @ManyToMany(mappedBy = "authorities", fetch = FetchType.EAGER)
    private Set<Role> roles;

    /**
     * Instantiates a new Authority.
     *
     * @param name the name of the authority
     */
    public Authority(String name) {
        this.name = name;
        this.roles = new HashSet<>();
    }

    @Override
    public String getAuthority() {
        return name;
    }
}
