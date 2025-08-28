package org.example.javabackend2.webbservice.services.impl;

import lombok.RequiredArgsConstructor;
import org.example.javabackend2.webbservice.dtos.UserDetailedDto;
import org.example.javabackend2.webbservice.mappers.UserMapper;
import org.example.javabackend2.webbservice.models.User;
import org.example.javabackend2.webbservice.repos.UserRepository;
import org.example.javabackend2.webbservice.services.UserService;
import org.springframework.stereotype.Service;

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
