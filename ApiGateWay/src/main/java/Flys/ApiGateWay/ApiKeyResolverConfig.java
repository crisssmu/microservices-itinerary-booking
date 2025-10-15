package Flys.ApiGateWay;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import reactor.core.publisher.Mono;

@Configuration
public class ApiKeyResolverConfig {
    @Bean
    public KeyResolver apiKeyResolver() {
        return exchange -> {
            // Busca la API key en varios headers posibles (case-insensitive)
            String apiKey = exchange.getRequest().getHeaders().getFirst("X-API-KEY");
            if (apiKey == null || apiKey.isEmpty()) {
                apiKey = exchange.getRequest().getHeaders().getFirst("X-Api-Key");
            }
            if (apiKey != null && !apiKey.isEmpty()) {
                return Mono.just(apiKey);
            }
            // Fallback: por usuario autenticado o IP remota
            String user = exchange.getRequest().getHeaders().getFirst("X-USER");
            if (user != null && !user.isEmpty()) {
                return Mono.just(user);
            }
            return Mono.justOrEmpty(exchange.getRequest().getRemoteAddress() != null ? exchange.getRequest().getRemoteAddress().getHostString() : "anonymous");
        };
    }
}
