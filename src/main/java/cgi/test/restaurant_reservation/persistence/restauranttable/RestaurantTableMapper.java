package cgi.test.restaurant_reservation.persistence.restauranttable;

import cgi.test.restaurant_reservation.controller.restauranttable.RestaurantTableDto;
import org.mapstruct.*;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface RestaurantTableMapper {
    RestaurantTable toEntity(RestaurantTableDto restaurantTableDto);

    List<RestaurantTableDto> toRestaurantTableDtos(List<RestaurantTable> restaurantTables);

    @Mapping(target = "isAvailable", ignore = true)
    RestaurantTableDto toRestaurantTableDto (RestaurantTable restaurantTable);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateRestaurantTable(RestaurantTableDto restaurantTableDto, @MappingTarget RestaurantTable restaurantTable);
}