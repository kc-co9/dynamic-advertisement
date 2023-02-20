package com.share.co.kcl.dad.common.config;

import com.share.co.kcl.dad.common.sdk.CacheClient;
import com.share.co.kcl.dad.common.sdk.RedisCacheClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class BeanConfig {

    @Bean
    public CacheClient cacheClient(RedisTemplate<String, String> redisTemplate) {
        return new RedisCacheClient(redisTemplate);
    }

}
