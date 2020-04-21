package com.yhy.gmall.pms.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class PmsRedisConfig {

    @Bean
    public RedisTemplate redisSerializer(RedisConnectionFactory factory){
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        //创建 Jackson2JsonRedisSerializer 序列方式，对象类型使用 Object 类型，
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        //设置一下 jackJson 的 ObjectMapper 对象参数
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
        // 设置 RedisTemplate 序列化规则。因为 key 通常是普通的字符串，所以使用 StringRedisSerializer 即可。
        // 而 value 是对象时，才需要使用序列化与反序列化
        // key 序列化规则
        template.setKeySerializer(new StringRedisSerializer());
        // value 序列化规则
        template.setValueSerializer(jackson2JsonRedisSerializer);
        // hash key 序列化规则
        template.setHashKeySerializer(new StringRedisSerializer());
        // hash value 序列化规则
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }
}