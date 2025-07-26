package com.authservice.service;

import org.springframework.stereotype.Service;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class RateLimiterService {
    private final Map<String, Bucket> cache = new ConcurrentHashMap<>();
    private final int requestsPerMinute = 100; // Лимит запросов

    public boolean tryConsume(String ip) {
        Bucket bucket = cache.computeIfAbsent(ip, this::createNewBucket);
        return bucket.tryConsume(1);
    }

    private Bucket createNewBucket(String key) {
        Refill refill = Refill.intervally(requestsPerMinute, Duration.ofMinutes(1));
        Bandwidth limit = Bandwidth.classic(requestsPerMinute, refill);
        return Bucket.builder().addLimit(limit).build();
    }
}