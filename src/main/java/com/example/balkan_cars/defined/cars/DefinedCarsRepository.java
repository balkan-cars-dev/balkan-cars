package com.example.balkan_cars.defined.cars;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DefinedCarsRepository extends JpaRepository<DefinedCars, UUID> {
    DefinedCars getByBusinessId(UUID businessId);

    Optional<DefinedCars> findByBusinessId(UUID businessId);
}
