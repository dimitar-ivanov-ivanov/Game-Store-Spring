package gamestore.controllers;

import gamestore.models.User;
import gamestore.security.UserRole;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("management/users")
public class UserManagementController {

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','ADMIN_TRAINEE')")
    public String getAllUsers() {
        return "pesho";
    }

    @PostMapping
    //@PreAuthorize("hasAuthority('user:write')")
    public void registerNewStudent(@RequestBody User user) {
        System.out.println("register new user");
        System.out.println(user);
    }

    // TODO: 4/9/2021 Update,delete

    @DeleteMapping(path = "{userId}")
    public void deleteStudent(@PathVariable Long userId) {
        throw new NotYetImplementedException();
    }

    @PutMapping(path = "{userId}")
    public void updateUser(@PathVariable Long userId,
                           @RequestBody User updatedUser) {
        throw new NotYetImplementedException();
    }
}
