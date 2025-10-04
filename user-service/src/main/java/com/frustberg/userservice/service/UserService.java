package com.frustberg.userservice.service;

import com.frustberg.userservice.model.User;
import com.frustberg.userservice.model.RefreshToken;
import com.frustberg.userservice.repository.UserRepository;
import com.frustberg.userservice.repository.RefreshTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("ROLE_USER");
        }
        return userRepository.save(user);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public boolean checkPassword(User user, String rawPassword) {
        return passwordEncoder.matches(rawPassword, user.getPassword());
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public RefreshToken createRefreshToken(String username, long durationSeconds) {
        RefreshToken rt = new RefreshToken();
        rt.setUsername(username);
        rt.setToken(UUID.randomUUID().toString());
        rt.setExpiryDate(Instant.now().plusSeconds(durationSeconds));
        return refreshTokenRepository.save(rt);
    }

    public Optional<RefreshToken> findRefreshToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public void revokeRefreshTokensForUser(String username) {
        refreshTokenRepository.deleteByUsername(username);
    }

    public List<com.frustberg.userservice.model.User> getAllUsers() {
        return userRepository.findAll();
    }

    public User updateUser(User user) {
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        return userRepository.save(user);
    }
}
