package com.example.balkan_cars.wishlist;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WishlistMapper {

    @Mapping(target = "id", ignore = true)
    Wishlist toEntity(WishlistDto dto);

    WishlistDto toDto(Wishlist wishlist);

    List<Wishlist> toEntities(List<WishlistDto> dtos);
    List<WishlistDto> toDtos(List<Wishlist> wishlists);
}
