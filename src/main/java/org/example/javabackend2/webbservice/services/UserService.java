package org.example.javabackend2.webbservice.services;

import org.example.javabackend2.webbservice.dtos.UserDetailedDto;

public interface UserService {

    public UserDetailedDto findUserDetailedDtoByEmail(String email);

    /*
    public UserDetailedDto userToUserDetailedDto(User user);

    public User userDetailedDtoToUser(UserDetailedDto userDetailedDto);

    public UserDto userToUserDto(User user);

    public User userDtoToUser(UserDto userDto);
     */

}
