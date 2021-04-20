package gamestore.controllers;

import gamestore.models.entities.user.User;
import gamestore.services.UserService;
import lombok.AllArgsConstructor;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * The User management controller.
 * Only for actions affecting all users
 */
@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserManagementController {

    private final UserService userService;

    /**
     * Gets all users.
     *
     * @return the all users
     */
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','ADMIN_TRAINEE')")
    public Collection<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // TODO: 4/9/2021 Find all users by email,name,find their roles,their games


}
