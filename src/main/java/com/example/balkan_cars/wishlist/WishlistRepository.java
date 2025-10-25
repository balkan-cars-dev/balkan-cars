package com.example.balkan_cars.wishlist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, UUID> {

    List<Wishlist> findAllByUserId(UUID userId);

    Optional<Wishlist> findByUserIdAndListingId(UUID userId, UUID listingId);
}
