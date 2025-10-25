package com.example.balkan_cars.wishlist;

import com.example.balkan_cars.listing.Listing;
import com.example.balkan_cars.shared.BaseEntity;
import com.example.balkan_cars.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "_whishlist")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Wishlist extends BaseEntity {

    @NotBlank
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @NotBlank
    @ManyToOne(fetch = FetchType.LAZY)
    private Listing listing;
}