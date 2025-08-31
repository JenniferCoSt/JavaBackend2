package org.example.javabackend2.webbservice.services.impl;

import lombok.RequiredArgsConstructor;
import org.example.javabackend2.webbservice.dtos.UserDetailedDto;
import org.example.javabackend2.webbservice.mappers.UserMapper;
import org.example.javabackend2.webbservice.models.User;
import org.example.javabackend2.webbservice.repos.UserRepository;
import org.example.javabackend2.webbservice.services.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDetailedDto findUserDetailedDtoByEmail(String email) {

        return userRepository.findByEmail(email)
                .map(userMapper::userToUserDetailedDto)
                .orElse(null);

    }

    @Override
    public boolean saveUser(User user) {
        Optional<User> optionalUser = userRepository.findByEmail(user.getEmail());
        if (optionalUser.isPresent()) {
            return false;
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userRepository.save(user);
        return true;
    }
}
