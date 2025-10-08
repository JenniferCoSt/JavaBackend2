package org.example.javabackend2.webbservice.serviceTesting;

import org.example.javabackend2.webbservice.dtos.RoleDto;
import org.example.javabackend2.webbservice.dtos.UserDetailedDto;
import org.example.javabackend2.webbservice.mappers.UserMapper;
import org.example.javabackend2.webbservice.models.Role;
import org.example.javabackend2.webbservice.models.User;
import org.example.javabackend2.webbservice.repos.UserRepository;
import org.example.javabackend2.webbservice.services.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    private UserDetailedDto userDto1;
    private UserDetailedDto userDto2;

    private User user1;
    private User user2;

    @BeforeEach
    void setUp() {
        userDto1 = UserDetailedDto.builder()
                .id(1L)
                .name("Pippi Långstrump")
                .email("villavillekulla@lindgren.se")
                .password("abcd")
                .role(new RoleDto(1L, "ADMIN"))
                .orders(Collections.emptyList()).build();


        userDto2 = UserDetailedDto.builder()
                .id(2L)
                .name("Emil Lönneberga")
                .email("snickerboa@lindgren.se")
                .password("1234")
                .role(new RoleDto(1L, "USER"))
                .orders(Collections.emptyList()).build();

        user1 = new User(1L, "Pippi Långstrump", "villavillekulla@lindgren.se", "abcd", new Role(1L, "ADMIN", Collections.emptyList()), new ArrayList<>());

        user2 = new User(2L, "Emil Lönneberga", "snickerboa@lindgren.se", "1234", new Role(2L, "USER", Collections.emptyList()), new ArrayList<>());

    }

    @Test
    void userService_findUserByEmail_ReturnsUserDto(){

        when(userRepository.findByEmail("villavillekulla@lindgren.se")).thenReturn(Optional.ofNullable(user1));
        when(userRepository.findByEmail("snickerboa@lindgren.se")).thenReturn(Optional.ofNullable(user2));

        when(userMapper.userToUserDetailedDto(user1)).thenReturn(userDto1);
        when(userMapper.userToUserDetailedDto(user2)).thenReturn(userDto2);

        UserDetailedDto result1 = userService.findUserDetailedDtoByEmail("villavillekulla@lindgren.se");
        UserDetailedDto result2 = userService.findUserDetailedDtoByEmail("snickerboa@lindgren.se");
        UserDetailedDto result3 = userService.findUserDetailedDtoByEmail("test@test.com");

        Assertions.assertEquals(result1.getEmail(), userDto1.getEmail());
        Assertions.assertEquals(result2.getEmail(), userDto2.getEmail());
        Assertions.assertEquals(result1.getName(), userDto1.getName());
        Assertions.assertEquals(result2.getRole(), userDto2.getRole());
        Assertions.assertNull(result3);

    }
}
