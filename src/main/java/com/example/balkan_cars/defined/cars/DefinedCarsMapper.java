package com.example.balkan_cars.defined.cars;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DefinedCarsMapper {
    
    @Mapping(source = "id", target = "businessId")
    @Mapping(target = "id", ignore = true)
    DefinedCars toEntity(DefinedCarsDto definedCarsDto);

    @Mapping(source = "businessId", target = "id")
    DefinedCarsDto toDto(DefinedCars definedCars);

    List<DefinedCars> toEntities(List<DefinedCarsDto> definedCarsDtos);
    List<DefinedCarsDto> toDtos(List<DefinedCars> definedCars);

    void updateEntityFromDto(DefinedCarsDto dto, @MappingTarget DefinedCars entity);
}
