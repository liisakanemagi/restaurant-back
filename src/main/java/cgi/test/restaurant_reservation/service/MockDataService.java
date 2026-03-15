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

    //AI abi
    @EventListener(ApplicationReadyEvent.class)
    public void setUpMockReservations() {

        reservationRepository.deleteAll();

        if (reservationRepository.count() > 0) {
            return;
        }
        List<RestaurantTable> restaurantTables = restaurantTableRepository.findAll();
        for (int day = 0; day < 14; day++) {
            DailyReservations result = getGenerateReservationsForThisDay(day);
            for(int i = 0; i < result.reservationsThisDay(); i++)
                createSingleMockReservation(restaurantTables, result.date(), day, i);
        }
    }

    private DailyReservations getGenerateReservationsForThisDay(int day) {
        LocalDateTime date = LocalDateTime.now().plusDays(day);
        int reservationsThisDay = 4 + random.nextInt(4);
        return new DailyReservations(date, reservationsThisDay);
    }

    private record DailyReservations(LocalDateTime date, int reservationsThisDay) {
    }

    private void createSingleMockReservation(List<RestaurantTable> restaurantTables, LocalDateTime date, int day, int i) {
        Reservation reservation = new Reservation();
        RestaurantTable restaurantTable = restaurantTables.get(random.nextInt(restaurantTables.size()));

        int randomHour = random.nextInt(12, 21);
        int randomMinutes = random.nextBoolean() ? 0 : 30;

        LocalDateTime startTime = date
                .withHour(randomHour)
                .withMinute(randomMinutes)
                .withSecond(0)
                .withNano(0);

        reservation.setRestaurantTable(restaurantTable);
        reservation.setStartTime(startTime);
        reservation.setEndTime(startTime.plusHours(2));
        reservation.setGuestCount(random.nextInt(1, restaurantTable.getCapacity() + 1));
        reservation.setCustomerName("Klient" + (day + 1) + "-" + (i + 1));
        reservation.setCustomerPhoneNumber("+372 555"+ (1000 + random.nextInt(9999)));
        reservationRepository.save(reservation);
    }

}


