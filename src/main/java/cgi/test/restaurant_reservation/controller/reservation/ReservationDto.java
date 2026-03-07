package cgi.test.restaurant_reservation.controller.reservation;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link cgi.test.restaurant_reservation.persistence.reservation.Reservation}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDto implements Serializable {
    private Integer id;

    private Integer restaurantTableId;

    @NotNull
    @Size(max = 100)
    private String customerName;

    @NotNull
    @Size(max = 100)
    private String customerPhoneNumber;

    @NotNull
    private LocalDateTime startTime;

    @NotNull
    private LocalDateTime endTime;

    @NotNull
    private Integer guestCount;
}