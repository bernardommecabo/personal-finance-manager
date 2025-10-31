package com.finance.personal.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserDTORequest {
    @NotBlank(message = "Username must not be null")
    private String name;

    @NotBlank(message = "Email must not be null")
    private String email;

    @NotBlank(message = "Password must not be null")
    @Size(min = 8,message = "The password must be at least 8 characters long")
    private String password;
}