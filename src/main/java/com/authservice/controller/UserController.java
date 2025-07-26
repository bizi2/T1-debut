package com.authservice.controller;

import com.authservice.dto.PasswordChangeRequest;
import com.authservice.dto.UserResponse;
import com.authservice.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    // Явный конструктор для инъекции зависимости
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    @PreAuthorize("hasAnyRole('USER', 'PREMIUM_USER', 'ADMIN')")
    public ResponseEntity<UserResponse> getCurrentUser(
            @RequestHeader("Authorization") String token
    ) {
        UserResponse currentUser = userService.getCurrentUser(token);
        return ResponseEntity.ok(currentUser);
    }

    @PutMapping("/password")
    @PreAuthorize("hasAnyRole('USER', 'PREMIUM_USER', 'ADMIN')")
    public ResponseEntity<Void> changePassword(
            @RequestHeader("Authorization") String token,
            @RequestBody PasswordChangeRequest request
    ) {
        userService.changePassword(token, request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/account")
    @PreAuthorize("hasAnyRole('USER', 'PREMIUM_USER', 'ADMIN')")
    public ResponseEntity<Void> deleteAccount(
            @RequestHeader("Authorization") String token
    ) {
        userService.deleteAccount(token);
        return ResponseEntity.noContent().build();
    }
}