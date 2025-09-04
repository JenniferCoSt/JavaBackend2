package org.example.javabackend2.webbservice.services.impl;

import lombok.RequiredArgsConstructor;
import org.example.javabackend2.webbservice.dtos.UserDetailedDto;
import org.example.javabackend2.webbservice.dtos.UserDto;
import org.example.javabackend2.webbservice.dtos.UserRegisterDto;
import org.example.javabackend2.webbservice.mappers.UserMapper;
import org.example.javabackend2.webbservice.models.Role;
import org.example.javabackend2.webbservice.models.User;
import org.example.javabackend2.webbservice.repos.RoleRepository;
import org.example.javabackend2.webbservice.repos.UserRepository;
import org.example.javabackend2.webbservice.services.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetailedDto findUserDetailedDtoByEmail(String email) {

        return userRepository.findByEmail(email)
                .map(userMapper::userToUserDetailedDto)
                .orElse(null);

    }

    @Override
    public boolean saveUser(UserRegisterDto userRegisterDto) {
        Optional<User> optionalUser = userRepository.findByEmail(userRegisterDto.getEmail());
        if (optionalUser.isPresent()) {
            return false;
        }
        Role role = roleRepository.findByType(userRegisterDto.getRole()).orElse(null);
        String encodedPassword = passwordEncoder.encode(userRegisterDto.getPassword());
        userRegisterDto.setPassword(encodedPassword);

        User user = userMapper.userRegisterDtoToUser(userRegisterDto, role);
        userRepository.save(user);
        return true;
    }

    @Override
    public UserDto findUserDtoByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if(user == null) {
            return null;
        }
        return userMapper.userToUserDto(user);
    }
}
