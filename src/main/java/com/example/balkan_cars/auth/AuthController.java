package com.example.balkan_cars.auth;

import com.example.balkan_cars.user.UserDto;
import com.example.balkan_cars.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Autowired
    private final UserService userService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password())
        );

        // Fetch the user to get their actual ID and name
        UserDto user = userService.findByEmail(request.email());
        String token = jwtUtil.generateToken(user); // Pass the whole DTO to JwtUtil

        return new LoginResponse(token, user.email(), user.id(), user.firstName());
    }

    @PostMapping("/register")
    public UserDto register(@RequestBody RegisterRequest request) {
        return userService.register(request);
    }
}
