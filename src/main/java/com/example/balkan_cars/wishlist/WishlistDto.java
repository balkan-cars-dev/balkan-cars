package com.example.balkan_cars.wishlist;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record WishlistDto(
        @NotBlank UUID id,
        @NotBlank UUID userId,
        @NotBlank UUID listingId
) {
}
