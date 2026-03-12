package cgi.test.restaurant_reservation.persistence.restauranttable;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@jakarta.persistence.Table(name = "restaurant_table")
public class RestaurantTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 10)
    @NotNull
    @Column(name = "table_number", nullable = false, length = 10)
    private String tableNumber;

    @NotNull
    @Column(name = "capacity", nullable = false)
    private Integer capacity;

    @NotNull
    @Column(name = "coordinate_x", nullable = false)
    private Double coordinateX;

    @NotNull
    @Column(name = "coordinate_y", nullable = false)
    private Double coordinateY;


    @ColumnDefault("false")
    @Column(name = "is_window_seat")
    private Boolean isWindowSeat = false;


    @ColumnDefault("false")
    @Column(name = "is_accessible")
    private Boolean isAccessible = false;


    @ColumnDefault("false")
    @Column(name = "is_private")
    private Boolean isPrivate = false;


}