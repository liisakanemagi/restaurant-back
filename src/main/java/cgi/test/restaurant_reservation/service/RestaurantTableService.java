package cgi.test.restaurant_reservation.service;

import cgi.test.restaurant_reservation.Infrastructure.DataNotFoundException;
import cgi.test.restaurant_reservation.Infrastructure.ErrorCode;
import cgi.test.restaurant_reservation.controller.restauranttable.RestaurantTableDto;
import cgi.test.restaurant_reservation.persistence.restauranttable.RestaurantTable;
import cgi.test.restaurant_reservation.persistence.restauranttable.RestaurantTableMapper;
import cgi.test.restaurant_reservation.persistence.restauranttable.RestaurantTableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantTableService {

    private final RestaurantTableRepository restaurantTableRepository;
    private final RestaurantTableMapper restaurantTableMapper;

    public RestaurantTable getValidRestaurantTable(Integer tableId){
        return restaurantTableRepository.findById(tableId)
                .orElseThrow(() -> new DataNotFoundException(ErrorCode.TABLE_NOT_FOUND));
    }

    public RestaurantTable updateRestaurantTable(Integer tableId, RestaurantTableDto restaurantTableDto){

        RestaurantTable restaurantTable = getValidRestaurantTable(tableId);
        restaurantTableMapper.updateRestaurantTable(restaurantTableDto, restaurantTable);

        restaurantTableRepository.save(restaurantTable);
        return restaurantTable;
    }


    public List<RestaurantTableDto> getRestaurantTables(){
        List<RestaurantTable> restaurantTables = restaurantTableRepository.findAll();
        return restaurantTableMapper.toRestaurantTableDtos(restaurantTables);
    }

}
