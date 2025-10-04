package com.frustberg.userservice.controller;

import com.frustberg.userservice.model.User;
import com.frustberg.userservice.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public User me(Authentication authentication) {
        String username = authentication.getName();
        return userService.findByUsername(username).orElse(null);
    }

    @PutMapping("/me")
    public User updateMe(Authentication authentication, @RequestBody User updated) {
        String username = authentication.getName();
        User user = userService.findByUsername(username).orElseThrow();
        if (updated.getUsername() != null) user.setUsername(updated.getUsername());
        if (updated.getPassword() != null && !updated.getPassword().isEmpty()) user.setPassword(updated.getPassword());
        return userService.updateUser(user);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public List<User> all() {
        return userService.getAllUsers();
    }
}
