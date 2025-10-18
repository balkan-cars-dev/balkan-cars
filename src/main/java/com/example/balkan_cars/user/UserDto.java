package com.example.balkan_cars.user;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record UserDto(
        @NotBlank UUID id,
        String firstName,
        String lastName,
        String nickname,
        String password,
        String phone,
        String email,
        String address,
        String city,
        String userType
) {}
