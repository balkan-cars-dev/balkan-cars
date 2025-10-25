package com.example.balkan_cars.parts;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PartMapper {

    @Mapping(source = "id", target = "businessId")
    @Mapping(target = "id", ignore = true)
    Part toEntity(PartDto dto);

    @Mapping(source = "businessId", target = "id")
    PartDto toDto(Part entity);

    List<Part> toEntities(List<PartDto> dtos);

    List<PartDto> toDtos(List<Part> entities);

    void updatePartFromDto(PartDto dto, @MappingTarget Part entity);
}
