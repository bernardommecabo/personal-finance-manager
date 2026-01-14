package com.finance.personal.service.validation;

import com.finance.personal.model.AccountEntity;
import com.finance.personal.model.CategoryEntity;
import org.springframework.stereotype.Component;

@Component
public class OwnershipValidator {
    public void validateAccountOwnership(AccountEntity accountEntity, Long id) {
        if (!accountEntity.getId().equals(id)) {
            throw new SecurityException("Access denied, this account does not belong to the user provided");
        }
    }

    public void validateCategoryOwnership(CategoryEntity categoryEntity, Long id) {
        if (!categoryEntity.getId().equals(id)) {
            throw new SecurityException("Access denied, this account does not belong to the user provided");
        }
    }
}
