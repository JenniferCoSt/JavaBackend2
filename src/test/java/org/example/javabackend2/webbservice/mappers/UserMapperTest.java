package org.example.javabackend2.webbservice.mappers;

import org.example.javabackend2.webbservice.dtos.RoleDto;
import org.example.javabackend2.webbservice.dtos.UserDetailedDto;
import org.example.javabackend2.webbservice.dtos.UserDto;
import org.example.javabackend2.webbservice.dtos.UserRegisterDto;
import org.example.javabackend2.webbservice.models.Role;
import org.example.javabackend2.webbservice.models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
public class UserMapperTest {


    @InjectMocks
    private UserMapper userMapper;

    Role user;
    RoleDto userDto;

    User user1;
    User user2;
    UserDto userDto1;
    UserDto userDto2;
    UserDetailedDto userDetailedDto1;
    UserDetailedDto userDetailedDto2;
    UserRegisterDto userRegisterDto1;
    UserRegisterDto userRegisterDto2;

    @BeforeEach
    void setUp() {

        user = new Role("user");
        userDto = new RoleDto(null,"user");

        user1 = User.builder()
                .name("Anders")
                .email("anders_andersson@gmail.com")
                .password("12345A")
                .role(user)
                .build();

        user2 = User.builder()
                .name("Bertil")
                .email("bertil_bertilsson@gmail.com")
                .password("12345B")
                .role(user)
                .build();

        userDto1 = UserDto.builder()
                .name("Anders")
                .build();

        userDto2 = UserDto.builder()
                .name("Bertil")
                .build();

        userDetailedDto1 = UserDetailedDto.builder()
                .name("Anders")
                .email("anders_andersson@gmail.com")
                .password("12345A")
                .role(userDto)
                .orders(Collections.emptyList())
                .build();

        userDetailedDto2 = UserDetailedDto.builder()
                .name("Bertil")
                .email("bertil_bertilsson@gmail.com")
                .password("12345B")
                .role(userDto)
                .orders(Collections.emptyList())
                .build();

        userRegisterDto1 = UserRegisterDto.builder()
                .name("Anders")
                .email("anders_andersson@gmail.com")
                .password("12345A")
                .role("user")
                .build();

        userRegisterDto2 = UserRegisterDto.builder()
                .name("Bertil")
                .email("bertil_bertilsson@gmail.com")
                .password("12345B")
                .role("user")
                .build();
    }

    @Test
    void userToUserDetailedDto() {
        UserDetailedDto result1 = userMapper.userToUserDetailedDto(user1);
        UserDetailedDto result2 = userMapper.userToUserDetailedDto(user2);

        Assertions.assertEquals(userDetailedDto1, result1);
        Assertions.assertEquals(userDetailedDto2, result2);
    }

    @Test
    void userToUserDto() {
        UserDto result1 = userMapper.userToUserDto(user1);
        UserDto result2 = userMapper.userToUserDto(user2);

        Assertions.assertEquals(userDto1, result1);
        Assertions.assertEquals(userDto2, result2);
    }

    @Test
    void userRegisterDtoToUser() {
        User result1 = userMapper.userRegisterDtoToUser(userRegisterDto1, user);
        User result2 = userMapper.userRegisterDtoToUser(userRegisterDto2, user);

        Assertions.assertEquals(user1, result1);
        Assertions.assertEquals(user2, result2);
    }
}