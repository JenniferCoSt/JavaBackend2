package org.example.javabackend2.mappers;

import org.example.javabackend2.dtos.RoleDto;
import org.example.javabackend2.dtos.UserDetailedDto;
import org.example.javabackend2.dtos.UserDto;
import org.example.javabackend2.models.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class UserMapper {

    public UserDetailedDto userToUserDetailedDto(User user) {
        return UserDetailedDto.builder().id(user.getId()).name(user.getName()).email(user.getEmail()).password(user.getPassword()).role(new RoleDto(user.getRole().getId(), user.getRole().getType())).orders(new ArrayList<>()).build();
    }

    public User userDetailedDtoToUser(UserDetailedDto userDetailedDto) {
        return null;
    }

    public UserDto userToUserDto(User user) {
        return null;
    }

    public User userDtoToUser(UserDto userDto) {
        return null;
    }
}
