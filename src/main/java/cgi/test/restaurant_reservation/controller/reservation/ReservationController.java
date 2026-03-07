package cgi.test.restaurant_reservation.controller.reservation;

import cgi.test.restaurant_reservation.service.ReservationService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ReservationController {

    private final ReservationService reservationService;

    @GetMapping("/api/reservations")
    @Operation(summary = "Reserveeringute pärimine", description = "Tagastab nimekirja reserveeringutest")

    public List<ReservationDto> getReservations() {
        return reservationService.getReservations();
    }

    @PostMapping("/api/reservation")
    @Operation(summary = "Reserveeringu tegemine", description = "Tagastab reserveeringu DTO objekti")

    public ReservationDto postReservation(@RequestBody ReservationInfo reservationInfo) {
        return reservationService.createReservation(reservationInfo);
    }

    @DeleteMapping("/api/reservation/{reservationId}")
    @Operation(summary = "Reserveeringu kustutamine")

    public void deleteReservation(@RequestParam Integer reservationId) {
        reservationService.deleteReservation(reservationId);
    }

}
