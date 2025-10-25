package com.example.balkan_cars.parts;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PartRepository extends JpaRepository<Part, UUID> {
    Part getByBussinesId(UUID businessId);

    Optional<Part> findByBissnesId(UUID businessId);
}
