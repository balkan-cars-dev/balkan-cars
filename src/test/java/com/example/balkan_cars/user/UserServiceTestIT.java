package com.example.balkan_cars.user;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserServiceTestIT {

    @Autowired
    private UserService serviceToTest;
    @Autowired
    private UserRepository userRepository;

    private User user;
    private UserDto userDto;

    @BeforeEach
    void setUp() {
        user = UserFixture.createUser();
        userDto = UserFixture.createUserDto();
    }

    @Test
    void shouldSaveUser() {
        UserDto result = serviceToTest.create(userDto);
        assertNotNull(result);
        assertEquals(result.id(), user.getBusinessId());
    }

    @Test
    void shouldFindUserById() {
        userRepository.save(user);
        UserDto result = serviceToTest.findById(userDto.id());
        assertNotNull(result);
        assertEquals(result, userDto);
    }

    @Test
    void shouldUpdateUser() {
        user.setCity("San Francisco");
        userRepository.save(user);
        UserDto result = serviceToTest.update(userDto);
        assertNotNull(result);
        assertEquals(result.city(), userDto.city());
    }

    @Test
    void shouldDeleteUser() {
        userRepository.save(user);
        serviceToTest.delete(userDto.id());
        assertEquals(serviceToTest.findAll().isEmpty(), true);
    }

    @Test
    void shouldFindAllUsers() {
        userRepository.save(user);
        List<UserDto> result = serviceToTest.findAll();
        assertNotNull(result);
        assertEquals(result.size(), 1);
    }

    @Test
    void shouldNotFindUserAndThrowException() {
        UUID notExistingId = UUID.randomUUID();
        assertThrows(EntityNotFoundException.class, () -> serviceToTest.findById(notExistingId));
    }
}