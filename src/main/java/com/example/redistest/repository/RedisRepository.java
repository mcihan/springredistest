package com.example.redistest.repository;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RedisRepository {

    private HashOperations hashOperations;
    private RedisTemplate redisTemplate;

    public RedisRepository(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.hashOperations = this.redisTemplate.opsForHash();
    }

    public void save(String aggregateId) {
        hashOperations.put("PAYMENT", String.valueOf(aggregateId.hashCode()), aggregateId);
    }

    public String findById(String aggregateId) {
        return (String) hashOperations.get("PAYMENT", String.valueOf(aggregateId.hashCode()));
    }

    public void delete(String aggregateId) {
        hashOperations.delete("PAYMENT", String.valueOf(aggregateId.hashCode()));
    }
}
