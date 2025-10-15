package Flys.Controllers;


import java.util.ArrayList;
import java.time.Duration;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Flys.dto.Flight;
import Flys.dto.FlightDetails;
import Flys.dto.Hotel;
import Flys.dto.Itinerary;
import Flys.dto.ItineraryResponse;
import Flys.dto.Price;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/itinerary/v1")

public class ItineraryController {

    private static final Logger log = LoggerFactory.getLogger(ItineraryController.class);

    @Autowired
    private InMemoryCache cache;
    
    // @Autowired
    // private RedisTemplate<String, ItineraryResponse> redisTemplate;

    private final ItineraryResponse response = new ItineraryResponse();


    @GetMapping("/search")
    public ResponseEntity<ItineraryResponse> search(
            @RequestParam String origin,
            @RequestParam String destination,
            @RequestParam String departDate,
            @RequestParam String returnDate,
            @RequestParam int adults,
            @RequestParam int rooms) {

        String cacheKey = String.format("itinerary:%s:%s:%s:%s:%d:%d", origin, destination, departDate, returnDate, adults, rooms);
        ItineraryResponse cached = cache.get(cacheKey);
        if (cached != null) {
            log.info("Cache HIT para la clave: {}", cacheKey);
            return ResponseEntity.ok().header("X-Cache", "HIT").body(cached);
        }

        log.info("Cache MISS para la clave: {}", cacheKey);
        log.info("Generando nueva respuesta y almacenando en caché...");
        response.setItineraries(new ArrayList<Itinerary>());
        response.setSearchId("SRCH-9c2b");
        response.getItineraries().add(new Itinerary() {{
            setId("ITI-BOG-MAD-20251210-20251220-001");
            setOrigin("Bogotá");
            setDestination("Madrid");
            setPrice(new Price() {{
                setCurrency("USD");
                setAmount(1890.50);
            }});
            setFlight(new Flight() {{
                setOutbound(new FlightDetails() {{
                    setCarrier("IB");
                    setNumber("IB6584");
                    setDepart("2025-12-10T21:10:00-05:00");
                    setArrive("2025-12-11T12:10:00+01:00");
                    setStops(0);
                }});
                setInbound(new FlightDetails() {{
                    setCarrier("IB");
                    setNumber("IB6583");
                    setDepart("2025-12-20T15:30:00+01:00");
                    setArrive("2025-12-20T20:30:00-05:00");
                    setStops(0);
                }});
            }});
            setHotel(new Hotel() {{
                setName("Hotel Gran Vía Madrid");
                setCheckIn("2025-12-11");
                setCheckOut("2025-12-20");
                setRoomType("Doble + extra");
                setRating(4.5);
                setAdults(adults);
                setRooms(rooms);
            }});
        }});
        try {
            if (origin.equalsIgnoreCase(response.getItineraries().get(0).getOrigin()) ||
                    destination.equalsIgnoreCase(response.getItineraries().get(0).getDestination()) ||
                    departDate.equalsIgnoreCase("2025-12-10") ||
                    returnDate.equalsIgnoreCase("2025-12-20") ||
                    adults == response.getItineraries().get(0).getHotel().getAdults() ||
                    rooms == response.getItineraries().get(0).getHotel().getRooms()) {
                cache.put(cacheKey, response, Duration.ofHours(2));
                return ResponseEntity.ok().header("X-Cache", "MISS").body(response);
            }
            throw new RuntimeException("No itineraries found with the given parameters");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage() != null ? new ItineraryResponse() : null);
        }
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<ItineraryResponse> details(@org.springframework.web.bind.annotation.PathVariable String id) {
        // Para demo, siempre retorna el mismo itinerario si el id coincide
        if (id.equalsIgnoreCase("ITI-BOG-MAD-20251210-20251220-001") || id.equalsIgnoreCase("ITI-12345")) {
            ItineraryResponse detailsResponse = new ItineraryResponse();
            detailsResponse.setItineraries(new ArrayList<Itinerary>());
            detailsResponse.setSearchId("SRCH-9c2b");
            detailsResponse.getItineraries().add(new Itinerary() {{
                setId(id);
                setOrigin("Bogotá");
                setDestination("Madrid");
                setPrice(new Price() {{
                    setCurrency("USD");
                    setAmount(1890.50);
                }});
                setFlight(new Flight() {{
                    setOutbound(new FlightDetails() {{
                        setCarrier("IB");
                        setNumber("IB6584");
                        setDepart("2025-12-10T21:10:00-05:00");
                        setArrive("2025-12-11T12:10:00+01:00");
                        setStops(0);
                    }});
                    setInbound(new FlightDetails() {{
                        setCarrier("IB");
                        setNumber("IB6583");
                        setDepart("2025-12-20T15:30:00+01:00");
                        setArrive("2025-12-20T20:30:00-05:00");
                        setStops(0);
                    }});
                }});
                setHotel(new Hotel() {{
                    setName("Hotel Gran Vía Madrid");
                    setCheckIn("2025-12-11");
                    setCheckOut("2025-12-20");
                    setRoomType("Doble + extra");
                    setRating(4.5);
                    setAdults(2);
                    setRooms(1);
                }});
            }});
            return ResponseEntity.ok(detailsResponse);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/user-itineraries")
    public ResponseEntity<String> userItineraries(@RequestParam String id) {
        return ResponseEntity.ok("user-itineraries");
    }

    @GetMapping("/user-itineraries")
    public ResponseEntity<String> userItinerariesGet(@RequestParam String id) {
        return ResponseEntity.ok("user-itineraries");
    }

    @DeleteMapping("/user-itineraries")
    public ResponseEntity<String> deleteUserItinerary(@RequestParam String id) {
        return ResponseEntity.ok("delete-user-itinerary");
    }
}