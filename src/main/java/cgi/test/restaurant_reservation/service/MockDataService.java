package cgi.test.restaurant_reservation.service;

import cgi.test.restaurant_reservation.persistence.reservation.Reservation;
import cgi.test.restaurant_reservation.persistence.reservation.ReservationRepository;
import cgi.test.restaurant_reservation.persistence.restauranttable.RestaurantTable;
import cgi.test.restaurant_reservation.persistence.restauranttable.RestaurantTableRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
public class MockDataService {

    private final Random random = new Random();
    private final RestaurantTableRepository restaurantTableRepository;
    private final ReservationRepository reservationRepository;

    public MockDataService(RestaurantTableRepository restaurantTableRepository, ReservationRepository reservationRepository) {
        this.restaurantTableRepository = restaurantTableRepository;
        this.reservationRepository = reservationRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void generateMockReservations() {
        if (reservationRepository.count() > 0) {
            return;
        }

        List<RestaurantTable> restaurantTables = restaurantTableRepository.findAll();
        for (RestaurantTable restaurantTable : restaurantTables) {

          int randomHour = random.nextInt(12, 21);
          int randomMinutes = random.nextBoolean() ? 0 : 30;

          LocalDateTime startTime = LocalDateTime.now()
                  .withHour(randomHour)
                  .withMinute(randomMinutes)
                  .withSecond(0)
                  .withNano(0);

          Reservation reservation = new Reservation();

          reservation.setRestaurantTable(restaurantTable);
          reservation.setStartTime(startTime);
          reservation.setEndTime(startTime.plusHours(2));
          reservation.setGuestCount(random.nextInt(1, restaurantTable.getCapacity() +1));
          reservation.setCustomerName("Klient");

          reservationRepository.save(reservation);
        }
    }

}


