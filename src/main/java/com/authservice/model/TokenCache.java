package com.authservice.model;

import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.annotation.Id;
import lombok.*;
import java.time.Instant;

@RedisHash(value = "Token", timeToLive = 86400)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TokenCache {
    @Id
    private String token;
    private String username;
    private Instant expiryDate;
}