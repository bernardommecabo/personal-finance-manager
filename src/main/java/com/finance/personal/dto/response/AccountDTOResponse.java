package com.finance.personal.dto.response;

import com.finance.personal.model.AccountEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTOResponse {
    private Long accountId;
    private String accountName;
    private String bankName;
    private BigDecimal balance;
    private String currency;

    public AccountDTOResponse(AccountEntity accountEntity) {
        this.accountId = accountEntity.getId();
        this.accountName = accountEntity.getName();
        this.bankName = accountEntity.getBankName();
        this.balance = accountEntity.getBalance();
        this.currency = accountEntity.getCurrency();
    }
}
