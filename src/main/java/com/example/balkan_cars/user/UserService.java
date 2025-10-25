package com.example.balkan_cars.user;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final String USER_NOT_FOUND = "User not found";
    
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }
    
    public List<UserDto> findAll() {
        return userMapper.toDtos(userRepository.findAll());
    }
    
    public UserDto findById(UUID id) {
        return userMapper.toDto(userRepository.findByBusinessId(id)
                .orElseThrow(() -> new EntityNotFoundException(USER_NOT_FOUND)));
    }
    
    @Transactional
    public UserDto create(UserDto userDto) {
        return userMapper.toDto(userRepository.save(userMapper.toEntity(userDto)));
    }

    @Transactional
    public UserDto update(UserDto userDto) {
        User user = userRepository.getByBusinessId(userDto.id());
        if (user == null) throw new EntityNotFoundException(USER_NOT_FOUND);
        userMapper.updateUserFromDto(userDto, user);
        return userMapper.toDto(userRepository.saveAndFlush(user));
    }
    
    @Transactional
    public void delete(UUID id) {
        User user = userRepository.getByBusinessId(id);
        if (user != null) userRepository.delete(user);
    }
}
