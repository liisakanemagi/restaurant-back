package cgi.test.restaurant_reservation.controller.restauranttable;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link cgi.test.restaurant_reservation.persistence.restauranttable.RestaurantTable}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantTableDto implements Serializable {
    private Integer id;

    @NotNull
    @Size(max = 10)
    private String tableNumber;

    @NotNull
    private Integer capacity;

    @NotNull
    private Double coordinateX;

    @NotNull
    private Double coordinateY;

    private Boolean isWindowSeat = false;

    private Boolean isAccessible = false;

    private Boolean isPrivate = false;

    private Boolean isAvailable;

}