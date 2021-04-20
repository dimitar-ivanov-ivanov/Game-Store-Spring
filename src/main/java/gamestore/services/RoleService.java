package gamestore.services;

import gamestore.utils.constants.TextConstants;
import gamestore.exceptions.RoleNotFoundException;
import gamestore.models.entities.security.Role;
import gamestore.repositories.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * The type Role service.
 *
 * @author Dimitar Ivanov
 */
@Service
@Transactional
@AllArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    /**
     * Gets role.
     *
     * @param name the role name
     * @return the role
     * @throws RoleNotFoundException
     * @see TextConstants#ROLE_NOT_FOUND
     * @see RoleRepository#getByName(String)
     */
    public Role getRole(String name) {
        return roleRepository.getByName(name)
                .orElseThrow(() -> new RoleNotFoundException(
                        TextConstants.ROLE_NOT_FOUND
                ));
    }
}
