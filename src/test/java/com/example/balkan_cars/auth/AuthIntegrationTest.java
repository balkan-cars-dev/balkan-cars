package com.example.balkan_cars.auth;

import com.example.balkan_cars.user.UserDto;
import com.example.balkan_cars.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserService userService;

    private UserDto testUser;

    @BeforeEach
    public void setup() {
        // Create a test user with full UserDto fields
        testUser = new UserDto(
                UUID.randomUUID(),
                "Test",
                "User",
                "tester",
                "password123",
                "1234567890",
                "test@example.com",
                "123 Test St",
                "TestCity",
                "USER"
        );

        userService.create(testUser);
    }

    @Test
    public void testLoginReturnsJwtToken() {
        LoginRequest loginRequest = new LoginRequest(testUser.email(), "password123");

        ResponseEntity<LoginResponse> response = restTemplate.postForEntity(
                "/auth/login",
                loginRequest,
                LoginResponse.class
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().token()).isNotEmpty();
    }

    @Test
    public void testAccessProtectedEndpointWithJwt() {
        // Step 1: Login to get JWT
        LoginRequest loginRequest = new LoginRequest(testUser.email(), "password123");
        ResponseEntity<LoginResponse> loginResponse = restTemplate.postForEntity(
                "/auth/login",
                loginRequest,
                LoginResponse.class
        );

        String token = loginResponse.getBody().token();

        // Step 2: Use JWT to access protected endpoint
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token); // sets Authorization: Bearer <token>
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<UserDto[]> response = restTemplate.exchange(
                "/users",
                HttpMethod.GET,
                entity,
                UserDto[].class
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotEmpty();
    }

    @Test
    public void testAccessProtectedEndpointWithoutJwtFails() {
        ResponseEntity<String> response = restTemplate.getForEntity("/users", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }
}
