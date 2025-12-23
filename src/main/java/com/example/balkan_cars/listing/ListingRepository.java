package com.example.balkan_cars.listing;

import com.example.balkan_cars.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ListingRepository extends JpaRepository<Listing, UUID> {

    List<Listing> findAllBySellerId(UUID sellerId);

    Listing getByBusinessId(UUID businessId);

    Optional<Listing> findByBusinessId(UUID businessId);
}
