package com.shiyc.cloud.config;

import com.shiyc.cloud.bean.RedisObjectBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.net.UnknownHostException;

@Configuration
public class MyRedisConfig {


    @Bean  // 自定义Redis的序列化器
    public RedisTemplate<Object, RedisObjectBean> redisTemplate(
            RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
        RedisTemplate<Object, RedisObjectBean> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(RedisObjectBean.class);
        template.setDefaultSerializer(jackson2JsonRedisSerializer);
        return template;
    }
}
