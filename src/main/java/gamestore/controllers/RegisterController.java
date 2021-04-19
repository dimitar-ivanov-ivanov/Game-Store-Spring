package gamestore.controllers;

import gamestore.models.bindings.UserRegisterBindingModel;
import gamestore.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("registration/user")
public class RegisterController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void registerNewStudent(@Valid @RequestBody UserRegisterBindingModel register) {
        userService.registerUser(register);
    }
}
