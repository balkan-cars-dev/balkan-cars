package com.example.balkan_cars.parts;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PartMapper {

    @Mapping(source = "id", target = "businessId")
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "imageUri", target = "imageUri")
    Part toEntity(PartDto dto);

    @Mapping(source = "businessId", target = "id")
    @Mapping(source = "imageUri", target = "imageUri")
    PartDto toDto(Part entity);

    List<Part> toEntities(List<PartDto> dtos);

    List<PartDto> toDtos(List<Part> entities);

    void updatePartFromDto(PartDto dto, @MappingTarget Part entity);

}
