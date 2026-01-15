package com.example.balkan_cars.parts;

import com.example.balkan_cars.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PartRepository extends JpaRepository<Part, UUID> {
    Part getByBusinessId(UUID businessId);

    Optional<Part> findByBusinessId(UUID businessId);
    
    List<Part> findAllBySeller(User seller);
}
