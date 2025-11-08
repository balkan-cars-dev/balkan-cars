package com.example.balkan_cars.user;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final String USER_NOT_FOUND = "User not found";
    
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    
    public List<UserDto> findAll() {
        return userMapper.toDtos(userRepository.findAll());
    }
    
    public UserDto findById(UUID id) {
        return userMapper.toDto(userRepository.findByBusinessId(id)
                .orElseThrow(() -> new EntityNotFoundException(USER_NOT_FOUND)));
    }
    
    @Transactional
    public UserDto create(UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userMapper.toDto(userRepository.save(user));
    }

    @Transactional
    public UserDto update(UserDto userDto) {
        User user = userRepository.getByBusinessId(userDto.id());
        if (user == null) throw new EntityNotFoundException(USER_NOT_FOUND);

        userMapper.updateUserFromDto(userDto, user);

        if (userDto.password() != null && !userDto.password().isEmpty()) {
            user.setPassword(passwordEncoder.encode(userDto.password()));
        }

        return userMapper.toDto(userRepository.save(user));
    }
    
    @Transactional
    public void delete(UUID id) {
        User user = userRepository.getByBusinessId(id);
        if (user != null) userRepository.delete(user);
    }
}
