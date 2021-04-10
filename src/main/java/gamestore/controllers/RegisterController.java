package gamestore.controllers;

import gamestore.models.bindings.UserRegisterBindingModel;
import gamestore.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("registration/user")
public class RegisterController {

    private final UserService userService;

    @PostMapping
    public void registerNewStudent(@RequestBody UserRegisterBindingModel register) {
        userService.registerUser(register);
    }
}