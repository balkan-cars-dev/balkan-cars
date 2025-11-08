package com.example.balkan_cars.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    User getByBusinessId(UUID businessId);

    Optional<User> findByEmail(String email);

    Optional<User> findByBusinessId(UUID businessId);
}
