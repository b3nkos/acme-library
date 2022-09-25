package co.edu.poli.acmelibrary.controllers;

import co.edu.poli.acmelibrary.dtos.UserDTO;
import co.edu.poli.acmelibrary.services.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserDTO saveNewUser(@RequestBody UserDTO userDTO) {
        return userService.createNewUser(userDTO);
    }
}
