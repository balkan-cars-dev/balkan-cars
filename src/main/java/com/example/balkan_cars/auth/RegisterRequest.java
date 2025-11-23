package com.example.balkan_cars.auth;

public record RegisterRequest(
        String email,
        String password,
        String firstName,
        String lastName,
        String phone,
        String address,
        String city
) {
}
