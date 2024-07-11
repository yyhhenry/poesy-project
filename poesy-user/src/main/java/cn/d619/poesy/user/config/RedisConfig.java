package cn.d619.poesy.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import cn.d619.poesy.user.pojo.po.PendingUserPO;

@Configuration
public class RedisConfig {
    @Bean
    RedisTemplate<String, PendingUserPO> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, PendingUserPO> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);

        Jackson2JsonRedisSerializer<PendingUserPO> serializer = new Jackson2JsonRedisSerializer<>(PendingUserPO.class);

        template.setValueSerializer(serializer);
        template.setKeySerializer(new StringRedisSerializer());
        template.afterPropertiesSet();
        return template;
    }
}
