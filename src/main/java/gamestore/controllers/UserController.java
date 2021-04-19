package gamestore.controllers;

import gamestore.models.dtos.UserGetDto;
import gamestore.models.entities.user.User;
import gamestore.services.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/users")
public class UserController {

    private final UserService userService;

    private final ModelMapper mapper;

    @GetMapping(path = "{userId}")
    @PreAuthorize("hasAuthority('user:read')")
    @ResponseBody
    public UserGetDto getUser(@PathVariable("userId") Long userId) {
        User user = userService.getById(userId);
        UserGetDto dto = mapper.map(user,UserGetDto.class);
        return dto;
    }

    // TODO: 4/9/2021 Find all users by email,name,find their roles,their games
}
