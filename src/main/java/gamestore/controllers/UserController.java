package gamestore.controllers;

import gamestore.models.entities.user.User;
import gamestore.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/users")
public class UserController {

    private final UserService userService;

    @GetMapping(path = "{userId}")
    @PreAuthorize("hasAuthority('user:read')")
    public User getUser(@PathVariable("userId") Long userId) {
        //send back dto
        return userService.getById(userId);
    }

    // TODO: 4/9/2021 Find all users by email,name,find their roles,their games
}
