package com.example.balkan_cars.vehicles.car;

import com.example.balkan_cars.enums.FuelType;
import com.example.balkan_cars.enums.TransmissionType;
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

    @NotBlank
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FuelType fuelType;

    @NotBlank
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransmissionType transmission;
}

