package com.example.balkan_cars.vehicles.car;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CarMapper {

    @Mapping(source = "id", target = "businessId")
    @Mapping(target = "id", ignore = true)
    Car toEntity(CarDto carDto);

    @Mapping(source = "businessId", target = "id")
    CarDto toDto(Car car);

    List<Car> toEntities(List<CarDto> carDtos);
    List<CarDto> toDtos(List<Car> cars);

    void updateCarFromDto(CarDto dto, @MappingTarget Car entity);
}
