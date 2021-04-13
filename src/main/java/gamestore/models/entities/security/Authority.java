package gamestore.models.entities.security;

import gamestore.constants.Messages;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "authorities")
public class Authority implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "authority_id")
    private Long privilegeId;

    @NotBlank(message = Messages.NAME_CANNOT_BE_BLANK)
    private String name;

    @ManyToMany(mappedBy = "authorities", fetch = FetchType.EAGER)
    private Set<Role> roles;

    public Authority(String name) {
        this.name = name;
        this.roles = new HashSet<>();
    }

    @Override
    public String getAuthority() {
        return name;
    }
}
