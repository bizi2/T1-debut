package com.authservice.service;

import com.authservice.dto.UserResponse;
import com.authservice.model.Role;
import java.util.List;
import java.util.Set;

public interface AdminService {
    List<UserResponse> getAllUsers();
    void updateUserRoles(Long userId, Set<Role> roles);
}