package cgi.test.restaurant_reservation.service;

import cgi.test.restaurant_reservation.Infrastructure.ErrorCode;
import cgi.test.restaurant_reservation.Infrastructure.ForbiddenException;
import cgi.test.restaurant_reservation.controller.reservation.ReservationDto;
import cgi.test.restaurant_reservation.controller.reservation.ReservationInfo;
import cgi.test.restaurant_reservation.persistence.reservation.Reservation;
import cgi.test.restaurant_reservation.persistence.reservation.ReservationMapper;
import cgi.test.restaurant_reservation.persistence.reservation.ReservationRepository;
import cgi.test.restaurant_reservation.persistence.restauranttable.RestaurantTable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;
    private final RestaurantTableService restaurantTableService;

    public List<ReservationDto> getReservations() {
        List<Reservation> reservations = reservationRepository.findAll();
        return reservationMapper.toReservationDtos(reservations);
    }

    public ReservationDto postReservation(ReservationInfo reservationInfo) {

        RestaurantTable restaurantTable = restaurantTableService.getValidRestaurantTable(reservationInfo.getRestaurantTableId());

        LocalDateTime endTime = reservationInfo.getStartTime().plusHours(2);

        validateTableAvailability(reservationInfo, restaurantTable, endTime);

        Reservation reservation = reservationMapper.toReservation(reservationInfo);
            reservation.setRestaurantTable(restaurantTable);
            reservation.setEndTime(endTime);
            Reservation savedReservation = reservationRepository.save(reservation);
            return reservationMapper.toReservationDto(savedReservation);
        }

    private void validateTableAvailability(ReservationInfo reservationInfo, RestaurantTable restaurantTable, LocalDateTime endTime) {
        boolean bookingAlreadyExists = reservationRepository.existsByRestaurantTableAndStartTimeBeforeAndEndTimeAfter
                (restaurantTable, endTime, reservationInfo.getStartTime());
        if (bookingAlreadyExists){
            throw new ForbiddenException(ErrorCode.RESTAURANT_TABLE_ALREADY_BOOKED);
        }
    }
}

