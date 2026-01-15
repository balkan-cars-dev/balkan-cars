package com.example.balkan_cars.defined.cars;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record DefinedCarsDto( 
 UUID id,             
 @NotBlank String brand) {
}
