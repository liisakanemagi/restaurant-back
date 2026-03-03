package cgi.test.restaurant_reservation.controller.restauranttable;


import cgi.test.restaurant_reservation.persistence.restauranttable.RestaurantTable;
import cgi.test.restaurant_reservation.service.RestaurantTableService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RestaurantTableController {


    private final RestaurantTableService restaurantTableService;

    @GetMapping("/api/restaurant/tables")
    @Operation(summary = "Laudade pärimine", description = "Tagastab nimekirja laudadest")

    public List<RestaurantTableDto> getRestaurantTables() {
        return restaurantTableService.getRestaurantTables();
    }

    @PutMapping("/restaurant/table/{tableId}")

    public RestaurantTable updateRestaurantTable(@PathVariable Integer tableId, @RequestBody RestaurantTableDto restaurantTableDto) {
        return restaurantTableService.updateRestaurantTable(tableId, restaurantTableDto);


    }

}
