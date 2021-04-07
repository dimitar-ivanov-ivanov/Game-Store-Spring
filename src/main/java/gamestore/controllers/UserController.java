package gamestore.controllers;

import com.google.common.collect.Sets;
import gamestore.models.User;
import gamestore.security.UserRole;
import gamestore.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping(params = "{userId}")
    public String getUser(@PathVariable("userId") Long userId) {
        return "pesho" + userId;
    }
}
