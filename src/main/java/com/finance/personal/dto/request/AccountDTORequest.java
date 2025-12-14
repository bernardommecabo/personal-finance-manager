package com.finance.personal.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class AccountDTORequest {
    @NotBlank (message = "Account name must not be null")
    private String name;

    @NotBlank (message = "Bank name must not be null")
    private String bankName;

    private BigDecimal balance;

    private String currency;

    private Long userId;
}
