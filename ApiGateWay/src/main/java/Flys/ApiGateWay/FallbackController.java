package Flys.ApiGateWay;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestController
public class FallbackController {
    @RequestMapping("/fallback/itinerary")
    public Mono<String> fallbackItinerary(ServerWebExchange exchange) {
        exchange.getResponse().setStatusCode(HttpStatus.SERVICE_UNAVAILABLE);
        return Mono.just("Servicio de itinerarios no disponible temporalmente. Intente más tarde.");
    }

    @RequestMapping("/fallback/booking")
    public Mono<String> fallbackBooking(ServerWebExchange exchange) {
        exchange.getResponse().setStatusCode(HttpStatus.SERVICE_UNAVAILABLE);
        return Mono.just("Servicio de reservas no disponible temporalmente. Intente más tarde.");
    }
}
