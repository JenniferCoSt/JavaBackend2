package org.example.javabackend2.services.impl;

import lombok.RequiredArgsConstructor;
import org.example.javabackend2.dtos.RoleDto;
import org.example.javabackend2.dtos.UserDetailedDto;
import org.example.javabackend2.dtos.UserDto;
import org.example.javabackend2.mappers.UserMapper;
import org.example.javabackend2.models.User;
import org.example.javabackend2.repos.UserRepository;
import org.example.javabackend2.services.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDetailedDto findUserDetailedDtoByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if(user == null) {
            return null;
        }
        return userMapper.userToUserDetailedDto(user);
    }
}
