package com.example.balkan_cars.listing;

import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ListingMapper {

    @Mapping(source = "id", target = "businessId")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "extras", source = "extras")
    Listing toEntity(ListingDto dto);

    @Mapping(source = "businessId", target = "id")
    @Mapping(target = "groupedExtras", expression = "java(listing.getGroupedExtras())")
    ListingDto toDto(Listing listing);

    List<ListingDto> toDtos(List<Listing> listings);

    void updateListingFromDto(ListingDto dto, @MappingTarget Listing entity);
}
