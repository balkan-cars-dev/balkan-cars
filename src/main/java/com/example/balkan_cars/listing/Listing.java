package com.example.balkan_cars.listing;

import com.example.balkan_cars.car.Car;
import com.example.balkan_cars.user.User;
import com.example.balkan_cars.shared.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "_listing")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Listing extends BaseEntity {

    @NotBlank
    @Column(nullable = false)
    private String title;

    @NotBlank
    @Column(nullable = false, length = 2000)
    private String description;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User seller;

    @Min(0)
    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private boolean isActive = true;
}
