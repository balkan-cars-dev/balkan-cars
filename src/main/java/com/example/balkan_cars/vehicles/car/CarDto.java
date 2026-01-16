package com.example.balkan_cars.vehicles.car;

import com.example.balkan_cars.enums.FuelType;
import com.example.balkan_cars.enums.TransmissionType;

import java.util.UUID;

public record CarDto(
        UUID id,
        String vin,
        String brand,
        String model,
        int year,
        FuelType fuelType,
        TransmissionType transmission,
        int mileage,
        int enginePower,
        String color,
        String image
) {
}
