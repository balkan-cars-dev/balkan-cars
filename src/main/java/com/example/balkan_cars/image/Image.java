package com.example.balkan_cars.image;

import com.example.balkan_cars.listing.Listing;
import com.example.balkan_cars.shared.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "_image")
public class Image extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private Listing listing;

    @NotBlank
    private String url;
}
