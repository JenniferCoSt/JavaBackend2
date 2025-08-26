package org.example.javabackend2.services;

import org.example.javabackend2.dtos.UserDetailedDto;
import org.example.javabackend2.dtos.UserDto;
import org.example.javabackend2.models.User;

public interface UserService {

    public UserDetailedDto findUserDetailedDtoByEmail(String email);

    /*
    public UserDetailedDto userToUserDetailedDto(User user);

    public User userDetailedDtoToUser(UserDetailedDto userDetailedDto);

    public UserDto userToUserDto(User user);

    public User userDtoToUser(UserDto userDto);
     */

}
