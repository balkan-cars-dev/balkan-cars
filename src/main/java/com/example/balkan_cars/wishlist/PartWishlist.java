package com.example.balkan_cars.wishlist;

import com.example.balkan_cars.parts.Part;
import com.example.balkan_cars.shared.BaseEntity;
import com.example.balkan_cars.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "_part_wishlist")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PartWishlist extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "part_id", nullable = false)
    private Part part;
}
