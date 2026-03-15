package cgi.test.restaurant_reservation.persistence.reservation;

import cgi.test.restaurant_reservation.persistence.restauranttable.RestaurantTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
// AI abi
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

   List<Reservation> findAllByStartTimeBeforeAndEndTimeAfter(LocalDateTime endTime, LocalDateTime startTime);

    boolean existsByRestaurantTableAndStartTimeBeforeAndEndTimeAfter(
            RestaurantTable restaurantTable, LocalDateTime endTime, LocalDateTime startTime);

}