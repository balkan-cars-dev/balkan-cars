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
    @Mapping(source = "dto.imageUriList", target = "imageUri", qualifiedByName = "mapImages")
    Part toEntity(PartDto dto);

    @Mapping(source = "businessId", target = "id")
    @Mapping(source = "imageUri", target = "imageUriList", qualifiedByName = "splitImages")
    PartDto toDto(Part entity);

    List<Part> toEntities(List<PartDto> dtos);

    List<PartDto> toDtos(List<Part> entities);

    void updatePartFromDto(PartDto dto, @MappingTarget Part entity);

    @Named("mapImages")
    default String mapImages(List<String> imageUriList) {
        if (imageUriList == null || imageUriList.isEmpty()) {
            return null;
        }
        return String.join(",", imageUriList); 
    }

    @Named("splitImages")
    default List<String> splitImages(String imageUris) {
        if (imageUris == null || imageUris.isBlank()) {
            return List.of();
        }
        return List.of(imageUris.split(","));
    }
    
}
