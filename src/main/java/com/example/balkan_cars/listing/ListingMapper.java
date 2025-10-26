package com.example.balkan_cars.listing;

import org.mapstruct.*;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ListingMapper {

    @Mapping(source = "id", target = "businessId")
    @Mapping(target = "id", ignore = true)
    Listing toEntity(ListingDto listingDto);

    @Mapping(source = "businessId", target = "id")
    ListingDto toDto(Listing listing);

    List<ListingDto> toDtos(List<Listing> listings);

    void updateListingFromDto(ListingDto dto, @MappingTarget Listing entity);
}

