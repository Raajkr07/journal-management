package com.example.journalapp.services;

import com.example.journalapp.apirespone.WeatherResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    public <T> T getKey(String key, Class<T> entityClass) {
        try {
            Object o = redisTemplate.opsForValue().get(key);
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(o.toString(), entityClass);
        }catch (Exception e) {
            System.out.println("Something happen in RedisService");
            return null;
        }
    }

    public void setKey(String key, Object o, long expiry) {
        try {
            redisTemplate.opsForValue().set(key, o.toString(), expiry, TimeUnit.SECONDS);
        }catch (Exception e) {
            System.out.println("Something happen in RedisService");
        }
    }
}
