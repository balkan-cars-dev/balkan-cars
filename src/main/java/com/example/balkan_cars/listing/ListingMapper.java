package com.example.balkan_cars.listing;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ListingMapper {

    @Mapping(source = "id", target = "businessId")
    @Mapping(target = "id", ignore = true)
    Listing toEntity(ListingDto listingDto);

    @Mapping(source = "businessId", target = "id")
    ListingDto toDto(Listing listing);

    List<Listing> toEntities(List<ListingDto> listingDtos);
    List<ListingDto> toDtos(List<Listing> listings);

    void updateListingFromDto(ListingDto dto, @MappingTarget Listing entity);
}
