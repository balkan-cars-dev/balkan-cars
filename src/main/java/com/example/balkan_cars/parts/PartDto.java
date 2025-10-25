package com.example.balkan_cars.parts;

import com.example.balkan_cars.enums.State;
import com.example.balkan_cars.enums.SubCategory;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record PartDto(
        @NotNull UUID id,
        @NotNull SubCategory subCategory,
        @NotNull State state,
        @DecimalMin(value = "0.0", inclusive = false)
        double price,
        @NotBlank String manufacturer
) {
}
