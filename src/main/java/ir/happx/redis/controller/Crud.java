package ir.happx.redis.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import ir.happx.redis.domain.BUser;
import ir.happx.redis.service.UserService;

@RestController
@RequestMapping("crud")
public class Crud {

    private final UserService userService;

    public Crud(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("get")
    public ResponseEntity<List<BUser>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @PostMapping("add")
    public void addUser(@RequestBody BUser user) {
        userService.addUser(user);
    }

    @PostMapping("edit")
    public ResponseEntity<String> editUser(@RequestBody BUser user) {
        return ResponseEntity.ok(userService.editUser(user));
    }

    @PostMapping("delete")
    public void deleteUser(String userId){
        userService.deleteUser(userId);
    }
}
