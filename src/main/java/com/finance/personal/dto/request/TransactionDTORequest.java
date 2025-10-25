package com.finance.personal.dto.request;

import com.finance.personal.model.CategoryEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TransactionDTORequest {

    @NotBlank
    private String name;

    @NotBlank
    private double amount;

    @NotNull
    private String description;

    private Long categoryId;
}
