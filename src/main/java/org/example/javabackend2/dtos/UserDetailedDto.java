package org.example.javabackend2.dtos;

import lombok.Builder;

import java.util.List;

@Builder
public class UserDetailedDto {

    private Long id;
    private String name;
    private String email;
    private String password;
    private RoleDto role;
    private List<OrderDto> orders;

}
