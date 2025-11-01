package com.example.balkan_cars.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/users")
public class UserController {
    
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
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
