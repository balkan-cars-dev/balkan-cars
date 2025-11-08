package com.example.balkan_cars.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    
    private final UserService userService;

    @GetMapping("{id}")
    public UserDto getUserById(@PathVariable String id) {
        return userService.findById(UUID.fromString(id));
    }
    
    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.findAll();
    }
    
    @PostMapping
    public UserDto createUser(@RequestParam UserDto userDto) {
        return userService.create(userDto);
    }
    
    @PutMapping
    public UserDto updateUser(@RequestParam UserDto userDto) {
        return userService.update(userDto);
    }
    
    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable String id) {
        userService.delete(UUID.fromString(id));
    }
}
