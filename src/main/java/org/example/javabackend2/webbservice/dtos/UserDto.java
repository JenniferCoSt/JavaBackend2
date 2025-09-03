package org.example.javabackend2.webbservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserDto {

    private Long id;
    private String name;

}
