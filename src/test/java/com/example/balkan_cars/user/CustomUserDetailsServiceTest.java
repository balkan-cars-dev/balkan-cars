package com.example.balkan_cars.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class CustomUserDetailsServiceTest {

    @Autowired
    private com.example.balkan_cars.security.CustomUserDetailsService userDetailsService;

    @Test
    void testLoadUserByUsername() {
        UserDetails userDetails = userDetailsService.loadUserByUsername("test@example.com");
        assertNotNull(userDetails);
        assertEquals("test@example.com", userDetails.getUsername());
    }
}
