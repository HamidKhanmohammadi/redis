package ir.happx.redis.controller;

import org.springframework.web.bind.annotation.*;

import ir.happx.redis.domain.BUser;
import ir.happx.redis.service.UserService;

@RestController
@RequestMapping("crud")
public class Crud {

    private final UserService userService;

    public Crud(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("add")
    public void addUser(@RequestBody BUser BUser) {
        userService.addUser(BUser);
    }
}
