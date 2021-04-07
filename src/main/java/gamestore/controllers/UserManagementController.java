package gamestore.controllers;

import gamestore.models.User;
import gamestore.security.UserRole;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("management/users")
public class UserManagementController {

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADMIN_TRAINEE')")
    public String getAllUsers() {
        return "pesho";
    }

    @PostMapping
    @PreAuthorize("hasAuthority('user:write')")
    public void registerNewStudent(@RequestBody User user) {
        System.out.println("register new user");
        System.out.println(user);
    }
}
