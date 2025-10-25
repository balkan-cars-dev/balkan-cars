package com.example.balkan_cars.listing;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ListingRepository extends JpaRepository<Listing, UUID> {

    Listing getByBusinessId(UUID businessId);

    Optional<Listing> findByBusinessId(UUID businessId);
}
