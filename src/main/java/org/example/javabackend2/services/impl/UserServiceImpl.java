package org.example.javabackend2.services.impl;

import lombok.RequiredArgsConstructor;
import org.example.javabackend2.dtos.UserDetailedDto;
import org.example.javabackend2.dtos.UserDto;
import org.example.javabackend2.models.User;
import org.example.javabackend2.repos.UserRepository;
import org.example.javabackend2.services.UserService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public UserDetailedDto userToUserDetailedDto(User user) {
        return null;
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
}
