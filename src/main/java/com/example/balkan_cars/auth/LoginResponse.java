package com.example.balkan_cars.auth;

import java.util.UUID;

public record LoginResponse(
        String token,
        String email,
        UUID userId,
        String firstName
) {}
