package com.example.balkan_cars.parts;

import com.example.balkan_cars.enums.State;
import com.example.balkan_cars.enums.SubCategory;
import com.example.balkan_cars.shared.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "_parts")
public class Part extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SubCategory subCategory;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private State state;

    @DecimalMin(value = "0.0", inclusive = false)
    @Column(nullable = false)
    private double price;

    @NotBlank
    @Column(nullable = false)
    private String manufacturer;

    @Column(nullable = false, length = 2000)
    private String description;
}
