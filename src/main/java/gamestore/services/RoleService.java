package gamestore.services;

import gamestore.constants.Messages;
import gamestore.exceptions.RoleNotFoundException;
import gamestore.models.entities.Role;
import gamestore.repositories.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public Role getRole(String name) {
        return roleRepository.getByName(name)
                .orElseThrow(() -> new RoleNotFoundException(
                        Messages.ROLE_NOT_FOUND
                ));
    }
}
