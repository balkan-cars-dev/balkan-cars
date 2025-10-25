package com.example.balkan_cars.vehicles;

import com.example.balkan_cars.shared.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class Vehicle extends BaseEntity {

    @NotBlank
    @Column(nullable = false, unique = true)
    private String vin;

    @NotBlank
    @Column(nullable = false)
    private String brand;

    @NotBlank
    @Column(nullable = false)
    private String model;

    @Min(1900)
    @Max(2100)
    @Column(nullable = false)
    private int year;

    @Min(0)
    private int mileage;

    @Min(0)
    private int enginePower;

    @NotBlank
    private String color;
}
