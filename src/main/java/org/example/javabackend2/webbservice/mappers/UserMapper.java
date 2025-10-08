package org.example.javabackend2.webbservice.mappers;

import org.example.javabackend2.webbservice.dtos.RoleDto;
import org.example.javabackend2.webbservice.dtos.UserDetailedDto;
import org.example.javabackend2.webbservice.dtos.UserDto;
import org.example.javabackend2.webbservice.dtos.UserRegisterDto;
import org.example.javabackend2.webbservice.models.Role;
import org.example.javabackend2.webbservice.models.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class UserMapper {

    public UserDetailedDto userToUserDetailedDto(User user) {
        return UserDetailedDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .role(new RoleDto(user.getRole().getId(), user.getRole().getType()))
                .orders(new ArrayList<>())
                .build();
    }

    public UserDto userToUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .build();
    }

    public User userRegisterDtoToUser(UserRegisterDto dto, Role role) {
        return User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .role(role).build();
    }
}
