package com.finance.personal.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserProfileDTORequest {
    @NotBlank(message = "Username must not be null")
    private String name;

    @NotBlank(message = "Email must not be null")
    private String email;
}
