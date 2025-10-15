package Flys.Controllers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import Flys.dto.ItineraryResponse;

@Configuration
public class RedisConfig {
    @Bean
    public RedisTemplate<String, ItineraryResponse> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, ItineraryResponse> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        
        // Configurar serializadores
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        
        template.afterPropertiesSet();
        return template;
    }
}
