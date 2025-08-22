package org.example.javabackend2.services.impl;

import lombok.RequiredArgsConstructor;
import org.example.javabackend2.dtos.RoleDto;
import org.example.javabackend2.dtos.UserDetailedDto;
import org.example.javabackend2.dtos.UserDto;
import org.example.javabackend2.models.User;
import org.example.javabackend2.repos.UserRepository;
import org.example.javabackend2.services.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDetailedDto userToUserDetailedDto(User user) {
        return UserDetailedDto.builder().id(user.getId()).name(user.getName()).email(user.getEmail()).password(user.getPassword()).role(new RoleDto(user.getRole().getId(), user.getRole().getType())).orders(new ArrayList<>()).build();
    }

    @Override
    public User userDetailedDtoToUser(UserDetailedDto userDetailedDto) {
        return null;
    }

    @Override
    public UserDto userToUserDto(User user) {
        return null;
    }

    @Override
    public User userDtoToUser(UserDto userDto) {
        return null;
    }

    @Override
    public UserDetailedDto findUserDetailedDtoByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return userToUserDetailedDto(user);
    }
}
