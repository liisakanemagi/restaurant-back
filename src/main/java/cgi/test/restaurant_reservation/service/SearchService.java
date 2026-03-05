package cgi.test.restaurant_reservation.service;

import cgi.test.restaurant_reservation.controller.restauranttable.RestaurantTableDto;
import cgi.test.restaurant_reservation.persistence.reservation.Reservation;
import cgi.test.restaurant_reservation.persistence.reservation.ReservationRepository;
import cgi.test.restaurant_reservation.persistence.restauranttable.RestaurantTable;
import cgi.test.restaurant_reservation.persistence.restauranttable.RestaurantTableMapper;
import cgi.test.restaurant_reservation.persistence.restauranttable.RestaurantTableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final ReservationRepository reservationRepository;
    private final RestaurantTableRepository restaurantTableRepository;
    private final RestaurantTableMapper restaurantTableMapper;

    public List<RestaurantTableDto> getFilteredRestaurantTables(LocalDateTime startTime, Integer guestCount,
                                                                Boolean isPrivate, Boolean isAccessible, Boolean isWindowSeat) {

        List<Integer> occupiedRestaurantTableIds = getOccupiedRestaurantTables(startTime);
        List<RestaurantTable> restaurantTables = restaurantTableRepository.findAll();

        List<RestaurantTable> suitableRestaurantTables = getSuitableRestaurantTables(guestCount, isPrivate, isAccessible,
                isWindowSeat, restaurantTables, occupiedRestaurantTableIds);

        sortSuitableRestaurantTables(guestCount, suitableRestaurantTables);

        return restaurantTableMapper.toRestaurantTableDtos(suitableRestaurantTables);
    }

    private List<Integer> getOccupiedRestaurantTables(LocalDateTime startTime) {
        LocalDateTime endTime = startTime.plusHours(2);
        List<Reservation> reservations = reservationRepository.findAllByStartTimeBeforeAndEndTimeAfter(endTime, startTime);
        return reservations.stream()
                .map(reservation -> reservation.getRestaurantTable().getId()).toList();
    }

    private static List<RestaurantTable> getSuitableRestaurantTables(Integer guestCount, Boolean isPrivate, Boolean isAccessible,
                                                                     Boolean isWindowSeat, List<RestaurantTable> restaurantTables,
                                                                     List<Integer> occupiedRestaurantTableIds) {
        List<RestaurantTable> suitableRestaurantTables = new ArrayList<>();
        for (RestaurantTable restaurantTable : restaurantTables) {
            if (!occupiedRestaurantTableIds.contains(restaurantTable.getId()) && restaurantTable.getCapacity() >= guestCount) {

                Boolean matchesPrivate = (isPrivate == null || restaurantTable.getIsPrivate().equals(isPrivate));
                Boolean matchesAccessible = (isAccessible == null || restaurantTable.getIsAccessible().equals(isAccessible));
                Boolean matchesWindowSeat = (isWindowSeat == null || restaurantTable.getIsWindowSeat().equals(isWindowSeat));
                if (matchesPrivate && matchesAccessible && matchesWindowSeat) {
                    suitableRestaurantTables.add(restaurantTable);
                }
            }
        }
        return suitableRestaurantTables;
    }

    private static void sortSuitableRestaurantTables(Integer guestCount, List<RestaurantTable> suitableRestaurantTables) {
        suitableRestaurantTables.sort((t1, t2) -> {
            int waste1 = t1.getCapacity() - guestCount;
            int waste2 = t2.getCapacity() - guestCount;
            return Integer.compare(waste1, waste2);
        });
    }

}

