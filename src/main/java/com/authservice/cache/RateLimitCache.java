package com.authservice.cache;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RateLimitCache {

    private final RedisTemplate<String, Integer> redisTemplate;
    private static final String RATE_LIMIT_KEY_PREFIX = "rate_limit:";

    public RateLimitCache(@Qualifier("integerRedisTemplate") RedisTemplate<String, Integer> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public boolean isRateLimited(String ipAddress, int maxRequests, long timeWindowMinutes) {
        String key = RATE_LIMIT_KEY_PREFIX + ipAddress;
        Integer currentCount = redisTemplate.opsForValue().get(key);

        if (currentCount == null) {
            redisTemplate.opsForValue().set(key, 1, timeWindowMinutes, TimeUnit.MINUTES);
            return false;
        }

        if (currentCount >= maxRequests) {
            return true;
        }

        redisTemplate.opsForValue().increment(key);
        return false;
    }

    public void resetRateLimit(String ipAddress) {
        redisTemplate.delete(RATE_LIMIT_KEY_PREFIX + ipAddress);
    }
}