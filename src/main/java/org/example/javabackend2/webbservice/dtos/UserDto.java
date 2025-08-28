package org.example.javabackend2.webbservice.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {

    private Long id;
    private String name;

}
