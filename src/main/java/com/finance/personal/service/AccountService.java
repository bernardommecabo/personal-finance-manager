package com.finance.personal.service;

import com.finance.personal.dto.request.AccountDTORequest;
import com.finance.personal.dto.response.AccountDTOResponse;
import com.finance.personal.dto.response.MessageDTOResponse;
import com.finance.personal.exception.DuplicatedItemException;
import com.finance.personal.exception.NotFoundException;
import com.finance.personal.model.AccountEntity;
import com.finance.personal.model.UserEntity;
import com.finance.personal.repository.AccountRepository;
import com.finance.personal.repository.UserRepository;
import com.finance.personal.service.validation.OwnershipValidator;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OwnershipValidator ownershipValidator;

    public AccountDTOResponse addAccount(AccountDTORequest accountDTORequest,Long userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));
        AccountEntity exist = accountRepository.findByName(accountDTORequest.getName());
        ownershipValidator.validateAccountOwnership(exist, userId);

        AccountEntity newAccount = new AccountEntity();
        newAccount.setName(accountDTORequest.getName());
        newAccount.setBankName(accountDTORequest.getBankName());
        if (accountDTORequest.getBalance() != null) {
            newAccount.setBalance(accountDTORequest.getBalance());
        }
        newAccount.setCurrency(accountDTORequest.getCurrency());
        newAccount.setUser(user);
        accountRepository.save(newAccount);
        return new AccountDTOResponse(newAccount);
    }

    public List<AccountDTOResponse> getAllUserAccounts(Long userId) {
        AccountEntity exist = accountRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));
        ownershipValidator.validateAccountOwnership(exist, userId);

        return accountRepository.findAllByUserId(userId)
                .stream()
                .map(AccountDTOResponse :: new)
                .toList();
    }

    public AccountDTOResponse getAccountById(Long accountId, Long userId) {
        AccountEntity account = accountRepository.findById(accountId)
                .orElseThrow(() -> new NotFoundException("Account ID: " + accountId + " not found"));
        ownershipValidator.validateAccountOwnership(account, userId);

        return new AccountDTOResponse(account);
    }

    public AccountDTOResponse updateAccount(AccountDTORequest request, Long userId, Long accountId) {
        AccountEntity account = accountRepository.findById(accountId)
                .orElseThrow(() -> new NotFoundException("Account ID: " + accountId + " not found"));
        ownershipValidator.validateAccountOwnership(account, userId);

        if (request.getName() != null) {
            AccountEntity nameExists = accountRepository.findByName(request.getName());
            if (nameExists != null && !nameExists.getId().equals(accountId)) {
                throw new DuplicatedItemException("This name already exists");
            }
            account.setName(request.getName());
        }

        account.setBalance(request.getBalance());
        account.setBankName(request.getBankName());
        account.setCurrency(request.getCurrency());
        accountRepository.save(account);
        return new AccountDTOResponse(account);
    }

    public MessageDTOResponse deleteAccount(Long userId, Long accountId){
        AccountEntity account = accountRepository.findById(accountId)
                .orElseThrow(() -> new NotFoundException("Account ID: " + accountId + " not found"));
        ownershipValidator.validateAccountOwnership(account, userId);

        MessageDTOResponse messageDTOResponse = new MessageDTOResponse();
        accountRepository.deleteById(accountId);
        messageDTOResponse.setMessage("Account ID: " + accountId + " has been deleted");
        return messageDTOResponse;
    }
}
