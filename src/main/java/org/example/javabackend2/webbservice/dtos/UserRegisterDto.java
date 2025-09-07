package org.example.javabackend2.webbservice.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRegisterDto {
    @Size(message ="Name must be between 2 and 50 characters", min = 2, max = 50)
    private String name;
    @Email(message = "Invalid email adress")
    private String email;
    @Size(message = "Password must be between 2 and 50 characters", min = 2, max = 50)
    private String password;
    @NotNull(message = "You have to choose a role")
    private String role;
}
