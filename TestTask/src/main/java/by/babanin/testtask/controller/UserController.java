package by.babanin.testtask.controller;

import by.babanin.testtask.entity.User;
import by.babanin.testtask.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registration")
    public User registration(@RequestBody User user) {
        return userService.addUser(user);
    }
}
