package com.example.redistest.service;


import com.example.redistest.repository.RedisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RedisService {

    private final RedisRepository redisRepository;

    public void save(String aggregateId) {
        redisRepository.save(aggregateId);
    }

    public String findById(String aggregateId) {
        return redisRepository.findById(aggregateId);
    }

    public void delete(String aggregateId) {
        redisRepository.delete(aggregateId);
    }
}
