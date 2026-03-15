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

        List<RestaurantTableDto> restaurantTableDtos = new ArrayList<>();

        for (RestaurantTable restaurantTable : restaurantTables) {
            RestaurantTableDto restaurantTableDto = restaurantTableMapper.toRestaurantTableDto(restaurantTable);

            Boolean isOccupied = (occupiedRestaurantTableIds.contains(restaurantTable.getId()));
            Boolean hasEnoughSeats = (guestCount == null || restaurantTable.getCapacity() >= guestCount);
            Boolean matchesPrivate = (isPrivate == null || restaurantTable.getIsPrivate().equals(isPrivate));
            Boolean matchesAccessible = (isAccessible == null || restaurantTable.getIsAccessible().equals(isAccessible));
            Boolean matchesWindowSeat = (isWindowSeat == null || restaurantTable.getIsWindowSeat().equals(isWindowSeat));

            restaurantTableDto.setIsAvailable(!isOccupied && hasEnoughSeats && matchesPrivate && matchesAccessible && matchesWindowSeat);
            restaurantTableDtos.add(restaurantTableDto);
        }

        sortSuitableRestaurantTables(guestCount, restaurantTableDtos);

       return restaurantTableDtos;
    }

    private List<Integer> getOccupiedRestaurantTables(LocalDateTime startTime) {
        LocalDateTime endTime = startTime.plusHours(2);
        List<Reservation> reservations = reservationRepository.findAllByStartTimeBeforeAndEndTimeAfter(endTime, startTime);
        return reservations.stream()
                .map(reservation -> reservation.getRestaurantTable().getId()).toList();
    }


    private static void sortSuitableRestaurantTables(Integer guestCount, List<RestaurantTableDto> restaurantTableDtos) {
        if (guestCount != null) {
            restaurantTableDtos.sort((t1, t2) -> {
                int waste1 = t1.getCapacity() - guestCount;
                int waste2 = t2.getCapacity() - guestCount;
                return Integer.compare(waste1, waste2);
            });
        }
    }

}