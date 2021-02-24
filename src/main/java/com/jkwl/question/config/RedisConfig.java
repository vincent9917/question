//package com.jkwl.question.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//
//@Component
//public class RedisConfig {
//    @Autowired
//    @Qualifier("redisTemplate")
//    RedisTemplate redisTemplate;
//
//    public RedisConfig() {
//    }
//
//    @PostConstruct
//    public void redisTemplate() {
//        this.redisTemplate.setKeySerializer(new StringRedisSerializer());
//        this.redisTemplate.setHashKeySerializer(new StringRedisSerializer());
//        this.redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
//        this.redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
//    }
//}
