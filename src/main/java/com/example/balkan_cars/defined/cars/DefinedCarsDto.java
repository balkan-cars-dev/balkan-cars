package com.example.balkan_cars.defined.cars;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record DefinedCarsDto( 
 UUID id,             
 @NotBlank String car,
 @NotBlank String model,
 @Min(1930)@Max(2025) short year) {
}
