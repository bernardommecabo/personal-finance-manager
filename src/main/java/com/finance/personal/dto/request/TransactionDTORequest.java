package com.finance.personal.dto.request;

import com.finance.personal.enums.TransactionType;
import com.finance.personal.model.CategoryEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class TransactionDTORequest {

    @NotBlank(message = "The transaction name must not be blank")
    private String name;

    @NotNull(message = "The transaction amount must not be null")
    private BigDecimal amount;

    private TransactionType type;

    private String description;

    private Long categoryId;

    private Long accountId;
}
