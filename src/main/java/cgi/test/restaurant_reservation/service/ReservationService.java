package cgi.test.restaurant_reservation.service;

import cgi.test.restaurant_reservation.Infrastructure.ErrorCode;
import cgi.test.restaurant_reservation.Infrastructure.exception.DataNotFoundException;
import cgi.test.restaurant_reservation.Infrastructure.exception.ForbiddenException;
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

    public Reservation getValidReservation(Integer reservationId) {
        return reservationRepository.findById(reservationId)
                .orElseThrow(() -> new DataNotFoundException(ErrorCode.RESERVATION_NOT_FOUND));
    }

    public List<ReservationDto> getReservations() {
        List<Reservation> reservations = reservationRepository.findAll();
        return reservationMapper.toReservationDtos(reservations);
    }

    public ReservationDto createReservation(ReservationInfo reservationInfo) {

        RestaurantTable restaurantTable = restaurantTableService.getValidRestaurantTable(reservationInfo.getRestaurantTableId());

        LocalDateTime endTime = reservationInfo.getStartTime().plusHours(2);

        validateTableAvailability(reservationInfo, restaurantTable, endTime);
        validateTableCapacity(reservationInfo, restaurantTable);
        validateCustomerNameExists(reservationInfo);
        validateCustomerPhoneNumberExists(reservationInfo);

        Reservation reservation = reservationMapper.toReservation(reservationInfo);
        reservation.setRestaurantTable(restaurantTable);
        reservation.setEndTime(endTime);
        Reservation savedReservation = reservationRepository.save(reservation);
        return reservationMapper.toReservationDto(savedReservation);
    }

    public void deleteReservation(Integer reservationId) {
        Reservation reservation = getValidReservation(reservationId);
        reservationRepository.delete(reservation);
    }

    private void validateTableAvailability(ReservationInfo reservationInfo, RestaurantTable restaurantTable, LocalDateTime endTime) {
        boolean bookingAlreadyExists = reservationRepository.existsByRestaurantTableAndStartTimeBeforeAndEndTimeAfter
                (restaurantTable, endTime, reservationInfo.getStartTime());
        if (bookingAlreadyExists) {
            throw new ForbiddenException(ErrorCode.RESTAURANT_TABLE_ALREADY_BOOKED);
        }
    }

    private static void validateTableCapacity(ReservationInfo reservationInfo, RestaurantTable restaurantTable) {
        if (restaurantTable.getCapacity() < reservationInfo.getGuestCount()) {
            throw new ForbiddenException(ErrorCode.RESTAURANT_TABLE_TOO_SMALL);
        }
    }

    private static void validateCustomerNameExists(ReservationInfo reservationInfo) {
        if (reservationInfo.getCustomerName() == null || reservationInfo.getCustomerName().isBlank()) {
            throw new ForbiddenException(ErrorCode.CUSTOMER_NAME_REQUIRED);
        }
    }

    private static void validateCustomerPhoneNumberExists(ReservationInfo reservationInfo) {
        if(reservationInfo.getCustomerPhoneNumber() == null || reservationInfo.getCustomerPhoneNumber().isBlank()) {
            throw new ForbiddenException(ErrorCode.CUSTOMER_PHONE_NUMBER_REQUIRED);
        }
    }

}

