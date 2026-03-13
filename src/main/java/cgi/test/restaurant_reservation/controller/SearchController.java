package cgi.test.restaurant_reservation.controller;

import cgi.test.restaurant_reservation.controller.restauranttable.RestaurantTableDto;
import cgi.test.restaurant_reservation.service.SearchService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    @GetMapping("/api/available")
    @Operation(summary = "Vabade & sobivate laudade pärimine")

    public List<RestaurantTableDto> getFilteredRestaurantTables(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime,
            @RequestParam(required = false) Integer guestCount,
            @RequestParam(required = false) Boolean isPrivate,
            @RequestParam(required = false) Boolean isAccessible,
            @RequestParam(required = false) Boolean isWindowSeat) {

        return searchService.getFilteredRestaurantTables(startTime, guestCount, isPrivate, isAccessible, isWindowSeat);

    }

}
