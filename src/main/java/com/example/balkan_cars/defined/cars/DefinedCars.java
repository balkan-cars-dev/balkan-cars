package com.example.balkan_cars.defined.cars;

import com.example.balkan_cars.shared.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "_defined_cars")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DefinedCars extends BaseEntity {
    
    @NotBlank
    @Column(name = "car", nullable = false)
    private String car;

    @NotBlank
    @Column(name = "model", nullable = false)
    private String model;

    @Min(1930)
    @Max(2025)
    @Column(name = "year", nullable = false)
    private short year;

}
