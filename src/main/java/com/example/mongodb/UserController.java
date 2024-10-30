package com.example.mongodb;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/user")
    public ResponseEntity<String> add(@RequestBody User user) {
        String savedId = userService.save(user);
        return ResponseEntity.ok(savedId);
    }

    @PutMapping(value = "/user/{id}")
    public ResponseEntity<Void> update(@PathVariable String id, @RequestBody User user) {
        userService.update(id, user);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/user/{id}")
    public ResponseEntity<User> getById(@PathVariable String id) {
        User user = userService.findById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping(value = "/user")
    public ResponseEntity<List<User>> getAll() {
        List<User> page = userService.findAll();
        return ResponseEntity.ok().body(page);
    }

    @DeleteMapping(value = "/user/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
