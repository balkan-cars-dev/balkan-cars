package com.example.balkan_cars.car;

import com.example.balkan_cars.enums.FuelType;
import com.example.balkan_cars.enums.TransmissionType;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record CarDto(
        @NotBlank UUID id,
        String vin,
        String brand,
        String model,
        int year,
        FuelType fuelType,
        TransmissionType transmission,
        int mileage,
        int enginePower,
        String color
) {
}
