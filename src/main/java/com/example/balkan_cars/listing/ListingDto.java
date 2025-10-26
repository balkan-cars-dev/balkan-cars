package com.example.balkan_cars.listing;

import com.example.balkan_cars.listing.extras.ExtraType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.util.Set;
import java.util.UUID;

public record ListingDto(
        UUID id,
        @NotBlank String title,
        String description,
        UUID carId,
        UUID sellerId,
        @Min(0) int price,
        boolean isActive,
        Set<ExtraType> extras
) {}
