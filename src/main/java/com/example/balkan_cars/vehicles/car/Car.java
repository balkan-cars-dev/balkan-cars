package com.example.balkan_cars.vehicles.car;

import com.example.balkan_cars.vehicles.enums.FuelType;
import com.example.balkan_cars.vehicles.enums.TransmissionType;
import com.example.balkan_cars.vehicles.Vehicle;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "_car")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Car extends Vehicle {

    public Car(String vin, String brand, String model, int year, int mileage, int enginePower, String color, FuelType fuelType, TransmissionType transmission) {
        super(vin, brand, model, year, mileage, enginePower, color);
        this.fuelType = fuelType;
        this.transmission = transmission;
    }

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FuelType fuelType;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransmissionType transmission;
}

