package com.authservice.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class TokenCacheService {

    private final RedisTemplate<String, String> redisTemplate;

    // Явный конструктор для инъекции зависимости
    public TokenCacheService(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void cacheToken(String token, String username, Duration ttl) {
        redisTemplate.opsForValue().set(
                "token:" + username,
                token,
                ttl
        );
    }

    public boolean isTokenRevoked(String token) {
        return Boolean.TRUE.equals(
                redisTemplate.hasKey("revoked:" + token)
        );
    }

    public void revokeToken(String token) {
        redisTemplate.opsForValue().set(
                "revoked:" + token,
                "true",
                Duration.ofDays(1)
        );
    }
}