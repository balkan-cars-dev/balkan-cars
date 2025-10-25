package com.example.balkan_cars.car;

import com.example.balkan_cars.enums.FuelType;
import com.example.balkan_cars.enums.TransmissionType;
import com.example.balkan_cars.shared.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "_car")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Car extends BaseEntity {

    @NotBlank
    @Column(nullable = false, unique = true)
    private String vin;

    @NotBlank
    @Column(nullable = false)
    private String brand;

    @NotBlank
    @Column(nullable = false)
    private String model;

    @NotBlank
    @Min(1900)
    @Max(2100)
    @Column(nullable = false)
    private int year;

    @NotBlank
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FuelType fuelType;

    @NotBlank
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransmissionType transmission;

    @Min(0)
    private int mileage;

    @Min(0)
    private int enginePower;

    @NotBlank
    private String color;
}

