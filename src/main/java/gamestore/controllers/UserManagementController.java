package gamestore.controllers;

import gamestore.models.entities.user.User;
import gamestore.services.UserService;
import lombok.AllArgsConstructor;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@AllArgsConstructor
@RestController
@RequestMapping("management/users")
public class UserManagementController {

    private final UserService userService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','ADMIN_TRAINEE')")
    public Collection<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @DeleteMapping(path = "/delete/{userId}")
    @PreAuthorize("hasAuthority('user:delete')")
    public void deleteStudent(@PathVariable Long userId) {
        throw new NotYetImplementedException();
    }

    @PutMapping(path = "/update/{userId}")
    @PreAuthorize("hasAuthority('user:update')")
    public void updateUser(@PathVariable Long userId,
                           @RequestBody User updatedUser) {
        throw new NotYetImplementedException();
    }
}
