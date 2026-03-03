package cgi.test.restaurant_reservation.persistence.restauranttable;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RestaurantTableRepository extends JpaRepository<RestaurantTable, Integer> {


    Optional<RestaurantTable> findById(Integer id);
}