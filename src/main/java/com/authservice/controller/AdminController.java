package com.authservice.controller;

import com.authservice.dto.UserResponse;
import com.authservice.model.Role;
import com.authservice.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<UserResponse> users = adminService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/users/{id}/roles")
    public ResponseEntity<Void> updateUserRoles(
            @PathVariable Long id,
            @RequestBody Set<Role> roles
    ) {
        adminService.updateUserRoles(id, roles);
        return ResponseEntity.noContent().build();
    }
}