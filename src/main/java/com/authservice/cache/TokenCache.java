package com.authservice.cache;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import java.time.Duration;

@Component
public class TokenCache {

    private final RedisTemplate<String, String> redisTemplate;
    private static final String TOKEN_KEY_PREFIX = "token:";
    private static final String REVOKED_KEY_PREFIX = "revoked:";

    public TokenCache(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void cacheToken(String token, String username, long expirationMinutes) {
        String key = TOKEN_KEY_PREFIX + username;
        redisTemplate.opsForValue().set(
                key,
                token,
                Duration.ofMinutes(expirationMinutes)
        );
    }

    public String getToken(String username) {
        return redisTemplate.opsForValue().get(TOKEN_KEY_PREFIX + username);
    }

    public void revokeToken(String token) {
        redisTemplate.opsForValue().set(
                REVOKED_KEY_PREFIX + token,
                "true",
                Duration.ofDays(1)
        );
    }

    public boolean isTokenRevoked(String token) {
        return Boolean.TRUE.equals(
                redisTemplate.hasKey(REVOKED_KEY_PREFIX + token)
        );
    }
}