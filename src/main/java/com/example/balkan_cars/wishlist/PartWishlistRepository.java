package com.example.balkan_cars.wishlist;

import com.example.balkan_cars.parts.Part;
import com.example.balkan_cars.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PartWishlistRepository extends JpaRepository<PartWishlist, UUID> {
    List<PartWishlist> findAllByUser(User user);
    Optional<PartWishlist> findByUserAndPart(User user, Part part);
    void deleteByUserAndPart(User user, Part part);
}
