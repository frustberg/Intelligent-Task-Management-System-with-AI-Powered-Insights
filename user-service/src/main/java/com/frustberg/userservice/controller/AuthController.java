package com.frustberg.userservice.controller;

import com.frustberg.userservice.model.User;
import com.frustberg.userservice.model.RefreshToken;
import com.frustberg.userservice.security.JwtUtils;
import com.frustberg.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final JwtUtils jwtUtils;
    private final long refreshTokenDurationSeconds;

    public AuthController(UserService userService, JwtUtils jwtUtils,
                          @Value("${app.jwt.refresh-expiration-seconds:1209600}") long refreshTokenDurationSeconds) {
        this.userService = userService;
        this.jwtUtils = jwtUtils;
        this.refreshTokenDurationSeconds = refreshTokenDurationSeconds;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        if (userService.existsByUsername(user.getUsername())) {
            return ResponseEntity.badRequest().body("Username already exists");
        }
        User saved = userService.register(user);
        return ResponseEntity.ok(saved);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        Optional<User> userOpt = userService.findByUsername(user.getUsername());
        if (userOpt.isEmpty() || !userService.checkPassword(userOpt.get(), user.getPassword())) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
        User u = userOpt.get();
        String accessToken = jwtUtils.generateToken(u);
        RefreshToken refreshToken = userService.createRefreshToken(u.getUsername(), refreshTokenDurationSeconds);

        Map<String, Object> resp = new HashMap<>();
        resp.put("accessToken", accessToken);
        resp.put("refreshToken", refreshToken.getToken());
        resp.put("expiresIn", refreshTokenDurationSeconds);
        return ResponseEntity.ok(resp);
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestBody Map<String, String> body) {
        String rtoken = body.get("refreshToken");
        if (rtoken == null) return ResponseEntity.badRequest().body("refreshToken required");
        Optional<RefreshToken> rtOpt = userService.findRefreshToken(rtoken);
        if (rtOpt.isEmpty()) return ResponseEntity.status(401).body("Invalid refresh token");
        RefreshToken rt = rtOpt.get();
        if (rt.getExpiryDate().isBefore(java.time.Instant.now())) {
            return ResponseEntity.status(401).body("Refresh token expired"); 
        }
        Optional<User> userOpt = userService.findByUsername(rt.getUsername());
        if (userOpt.isEmpty()) return ResponseEntity.status(401).body("User not found for refresh token");
        String accessToken = jwtUtils.generateToken(userOpt.get());
        Map<String, Object> resp = new HashMap<>();
        resp.put("accessToken", accessToken);
        return ResponseEntity.ok(resp);
    }

    @PostMapping("/revoke")
    public ResponseEntity<?> revoke(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        if (username == null) return ResponseEntity.badRequest().body("username required");
        userService.revokeRefreshTokensForUser(username);
        return ResponseEntity.ok(Map.of("revoked", true));
    }
}
