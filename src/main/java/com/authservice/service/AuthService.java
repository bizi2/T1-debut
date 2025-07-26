package com.authservice.service;

import com.authservice.dto.*;
import com.authservice.model.User;
import com.authservice.repository.jpa.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenService jwtTokenService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final KafkaEventService kafkaEventService;

    // Явный конструктор для инъекции зависимостей
    public AuthService(
            AuthenticationManager authenticationManager,
            JwtTokenService jwtTokenService,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            KafkaEventService kafkaEventService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenService = jwtTokenService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.kafkaEventService = kafkaEventService;
    }

    public AuthResponse register(RegisterRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setRoles(request.getRoles());

        userRepository.save(user);
        kafkaEventService.sendUserRegisteredEvent(user);

        return generateTokens(user);
    }

    public AuthResponse login(AuthRequest request) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        User user = (User) auth.getPrincipal();
        kafkaEventService.sendUserLoggedInEvent(user);

        return generateTokens(user);
    }

    public AuthResponse refreshToken(String refreshToken) {
        String username = jwtTokenService.getUsernameFromToken(refreshToken);
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return generateTokens(user);
    }

    private AuthResponse generateTokens(User user) {
        String accessToken = jwtTokenService.generateToken(
                user.getUsername(),
                user.getAuthorities()
        );

        String refreshToken = jwtTokenService.generateToken(
                user.getUsername(),
                Collections.emptyList()
        );

        AuthResponse response = new AuthResponse();
        response.setAccessToken(accessToken);
        response.setRefreshToken(refreshToken);
        return response;
    }
}