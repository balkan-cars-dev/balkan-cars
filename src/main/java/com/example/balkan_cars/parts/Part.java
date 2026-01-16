package com.example.balkan_cars.parts;

import com.example.balkan_cars.defined.cars.DefinedCars;
import com.example.balkan_cars.enums.State;
import com.example.balkan_cars.enums.SubCategory;
import com.example.balkan_cars.shared.BaseEntity;
import com.example.balkan_cars.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "_parts")
public class Part extends BaseEntity {
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id")
    private User seller;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SubCategory subCategory;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private State state;

    @DecimalMin(value = "0.00", inclusive = false)
    @Column(nullable = false)
    private double price;

    @NotBlank
    @Column(nullable = false)
    private String manufacturer;

    @Column(nullable = false, length = 2000)
    private String description;
    
    @Column(nullable = false)
    private String car;
    
    @Column(nullable = false)
    @Min(1)
    private byte quantity;
    
    @Column(name = "image_uri", length = 3000000)
    private String imageUri;
    
    @Column(name = "phone")
    private String phone;
}
