package com.example.balkan_cars.car;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CarRepository extends JpaRepository<Car, UUID> {
    Car getByBusinessId(UUID businessId);

    Optional<Car> findByBusinessId(UUID businessId);
}
