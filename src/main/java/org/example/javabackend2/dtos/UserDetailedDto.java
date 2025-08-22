package org.example.javabackend2.dtos;

import java.util.List;

public class UserDetailedDto {

    private Long id;
    private String name;
    private String email;
    private String password;
    private RoleDto role;
    private List<OrderDto> orders;

}
