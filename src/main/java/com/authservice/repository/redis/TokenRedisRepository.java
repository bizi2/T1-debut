package com.authservice.repository.redis;

import com.authservice.cache.TokenCache;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import java.util.Optional;

@EnableRedisRepositories
public interface TokenRedisRepository extends CrudRepository<TokenCache, String> {
    Optional<TokenCache> findByToken(String token);
}