package Flys.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Flys.models.Money;
import Flys.models.Reservation;
import Flys.models.ReservationDto;

@RestController
@RequestMapping("/api/booking/v1/reservations")
public class ReservationsController {

    @PostMapping
public ReservationDto createReservation(@RequestBody Reservation reservation) {
    ReservationDto response = new ReservationDto();
    response.setReservationId("RES-9001001");
    response.setStatus("CONFIRMED");
    response.setHotelConfirmation("H-77XYZ");

    Money total = new Money();
    total.setCurrency("USD");
    total.setAmount(1890.50);
    response.setMoney(total);

    return response;
}

    @GetMapping("/{reservationId}")
    public ReservationDto getReservationById(@PathVariable String reservationId) {
        if ("RES-9001".equals(reservationId)) {
            ReservationDto response = new ReservationDto();
            response.setReservationId("RES-9001");
            response.setStatus("CONFIRMED");
            response.setHotelConfirmation("H-77XYZ");

            Money total = new Money();
            total.setCurrency("USD");
            total.setAmount(1890.50);
            response.setMoney(total);

            return response;
        }
        return null; // O puedes lanzar una excepción si no existe
    }
}

