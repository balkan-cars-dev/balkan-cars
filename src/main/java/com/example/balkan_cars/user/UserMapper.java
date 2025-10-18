package com.example.balkan_cars.user;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    
    @Mapping(source = "id", target = "businessId")
    @Mapping(target = "id", ignore = true)
    User toEntity(UserDto userDto);
    
    @Mapping(source = "businessId", target = "id")
    UserDto toDto(User user);
    
    List<User> toEntities(List<UserDto> userDtos);
    List<UserDto> toDtos(List<User> users);
    
    void updateUserFromDto(UserDto dto, @MappingTarget User entity);
}
