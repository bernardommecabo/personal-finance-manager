package com.finance.personal.service;

import com.finance.personal.dto.request.TransactionDTORequest;
import com.finance.personal.model.TransactionEntity;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    public void createNewTransaction(TransactionDTORequest request) {
        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setName(request.getName());
        transactionEntity.setDescription(request.getDescription());
//        transactionEntity.setCategory(request.getCategory());
        //repository.save(transactionEntity)
    }
}
