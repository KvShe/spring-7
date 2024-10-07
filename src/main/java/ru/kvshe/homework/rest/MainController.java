package ru.kvshe.homework.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.kvshe.homework.model.MyUser;
import ru.kvshe.homework.service.UserService;

@RestController
@RequiredArgsConstructor
public class MainController {
    private final UserService userService;


    @PostMapping("new-user")
    public String createUser(@RequestBody MyUser user) {
        userService.createUser(user);
        return "User created";
    }
}
