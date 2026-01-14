package com.finance.personal.service;

import com.finance.personal.dto.request.TransactionDTORequest;
import com.finance.personal.dto.response.MessageDTOResponse;
import com.finance.personal.dto.response.TransactionDTOResponse;
import com.finance.personal.enums.TransactionType;
import com.finance.personal.exception.DuplicatedItemException;
import com.finance.personal.exception.NotFoundException;
import com.finance.personal.model.AccountEntity;
import com.finance.personal.model.CategoryEntity;
import com.finance.personal.model.TransactionEntity;
import com.finance.personal.repository.AccountRepository;
import com.finance.personal.repository.CategoryRepository;
import com.finance.personal.repository.TransactionRepository;
import com.finance.personal.service.validation.OwnershipValidator;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private OwnershipValidator ownershipValidator;

    @Transactional
    public TransactionDTOResponse createNewTransaction(TransactionDTORequest request, Long accountId, Long userId) {
        AccountEntity account = accountRepository.findById(accountId)
                .orElseThrow(() -> new NotFoundException("Account not found"));
        ownershipValidator.validateAccountOwnership(account, userId);

        CategoryEntity category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new NotFoundException("Category not found"));
        ownershipValidator.validateAccountOwnership(account, userId);

        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setAccount(account);
        transactionEntity.setName(request.getName());
        transactionEntity.setAmount(request.getAmount());
        transactionEntity.setDescription(request.getDescription());
        transactionEntity.setDate(LocalDateTime.now());
        transactionEntity.setCategory(category);
        transactionEntity.setType(request.getType());

        BigDecimal currentBalance = account.getBalance();
        if (currentBalance == null) {
            currentBalance = BigDecimal.ZERO;
        }

        if (request.getType() == TransactionType.INCOME){
            account.setBalance(currentBalance.add(request.getAmount()));
        }
        else if (request.getType() == TransactionType.EXPENSE) {
            if (currentBalance.subtract(request.getAmount()).compareTo(BigDecimal.ZERO) < 0) {
                throw new IllegalArgumentException("Not enough balance");
            }
            account.setBalance(currentBalance.subtract(request.getAmount()));
        }
        accountRepository.save(account);
        transactionRepository.save(transactionEntity);
        return new TransactionDTOResponse(transactionEntity);
    }

    public List<TransactionDTOResponse> getAllTransactions(Long accountId) {
        return transactionRepository.findAllByAccountId(accountId)
                .stream()
                .map(TransactionDTOResponse::new)
                .toList();
    }

    public TransactionDTOResponse getTransactionById(Long transactionId) {
        TransactionEntity transactionEntity = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new NotFoundException("Transaction not found"));
        return new TransactionDTOResponse(transactionEntity);
    }

    public TransactionDTOResponse updateTransaction(Long transactionId, TransactionDTORequest request, Long accountId) {
        AccountEntity account =  accountRepository.findById(accountId)
                .orElseThrow(() -> new NotFoundException("Account not found"));

        TransactionEntity transactionEntity = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new NotFoundException("Transaction not found"));

        CategoryEntity categoryEntity = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new NotFoundException("Category not found"));

        if (!transactionEntity.getAccount().getId().equals(account.getId())) {
            throw new SecurityException("Access denied");
        }
        transactionEntity.setName(request.getName());
        transactionEntity.setAmount(request.getAmount());
        transactionEntity.setDescription(request.getDescription());
        transactionEntity.setCategory(categoryEntity);
        transactionEntity.setType(request.getType());

        BigDecimal currentBalance = account.getBalance();
        if (request.getType() == TransactionType.INCOME){
            account.setBalance(currentBalance.add(request.getAmount()));
        }
        else if (request.getType() == TransactionType.EXPENSE) {
            if (currentBalance.subtract(request.getAmount()).compareTo(BigDecimal.ZERO) < 0) {
                throw new IllegalArgumentException("Not enough balance");
            }
            account.setBalance(currentBalance.subtract(request.getAmount()));
        }
        accountRepository.save(account);
        transactionRepository.save(transactionEntity);
        return new TransactionDTOResponse(transactionEntity);
    }

    public MessageDTOResponse deleteTransaction(Long transactionId) {
        TransactionEntity transactionEntity = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new NotFoundException("Transaction not found"));

        MessageDTOResponse messageDTOResponse = new MessageDTOResponse();
        transactionRepository.delete(transactionEntity);
        messageDTOResponse.setMessage("Transaction has been deleted");
        return messageDTOResponse;
    }
}
