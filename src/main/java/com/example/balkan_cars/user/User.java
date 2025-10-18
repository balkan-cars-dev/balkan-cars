package com.example.balkan_cars.user;

import com.example.balkan_cars.enums.UserType;
import com.example.balkan_cars.shared.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "_user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {
    @NotBlank
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @NotBlank
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @NotBlank
    @Column(name = "email", nullable = false)
    private String email;
    @NotBlank
    @Column(name = "nickname", nullable = false)
    private String nickname;
    @NotBlank
    @Column(name = "password", nullable = false)
    private String password;
    @NotBlank
    @Column(name = "phone", nullable = false)
    private String phone;
    @NotBlank
    @Column(name = "address", nullable = false)
    private String address;
    @NotBlank
    @Column(name = "city")
    private String city;
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "user_type", nullable = false, updatable = false)
    private UserType userType;
    @Column(name = "last_login")
    private Instant lastLogin;
}
