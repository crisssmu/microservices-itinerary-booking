package Flys.ApiGateWay;

import org.springframework.cloud.gateway.filter.factory.rewrite.RewriteFunction;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;

@Component
public class MaskResponseFilter implements RewriteFunction<String, String> {
    @Override
    public Mono<String> apply(org.springframework.web.server.ServerWebExchange exchange, String originalBody) {
        if (originalBody == null) return Mono.just("");
        String masked = originalBody.replaceAll("\\b[\\w.%+-]+@[\\w.-]+\\.[A-Za-z]{2,6}\\b", "***@***.***");
        return Mono.just(masked);
    }
}
    