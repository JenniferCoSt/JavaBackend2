package org.example.javabackend2.webbservice.dtos;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserDetailedDto {

    private Long id;
    private String name;
    private String email;
    private String password;
    private RoleDto role;
    private List<OrderDto> orders;

}
