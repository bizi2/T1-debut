package com.authservice.service;

import com.authservice.dto.PasswordChangeRequest;
import com.authservice.dto.UserResponse;
import com.authservice.model.Role;
import com.authservice.model.User;

import java.util.List;
import java.util.Set;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(Long id);
    User updateUserRoles(Long id, Set<Role> roles);
    UserResponse getCurrentUser(String token);
    void changePassword(String token, PasswordChangeRequest request);
    void deleteAccount(String token);
}