package com.finance.personal.dto.response;

import com.finance.personal.model.TransactionEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTOResponse {
    private Long id;
    private String name;
    private BigDecimal amount;
    private String description;
    private LocalDateTime date;
    private Long categoryId;
    private Long accountId;

    public TransactionDTOResponse(TransactionEntity transaction) {
        this.id = transaction.getId();
        this.name = transaction.getName();
        this.amount = transaction.getAmount();
        this.description = transaction.getDescription();
        this.date = transaction.getDate();
        this.categoryId = transaction.getCategory().getId();
        this.accountId = transaction.getAccount().getId();
    }
}
