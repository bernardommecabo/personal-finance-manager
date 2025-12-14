package com.finance.personal.controller;

import com.finance.personal.dto.request.AccountDTORequest;
import com.finance.personal.dto.response.AccountDTOResponse;
import com.finance.personal.dto.response.MessageDTOResponse;
import com.finance.personal.service.AccountService;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@Validated
@RequestMapping("/user/{userId}/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping
    public ResponseEntity<AccountDTOResponse> addAccount(@PathVariable Long userId, @RequestBody @Valid AccountDTORequest accountDTORequest) {
        AccountDTOResponse accountDTOResponse = accountService.addAccount(accountDTORequest,userId);
        return new ResponseEntity<>(accountDTOResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AccountDTOResponse>> getAllUserAccounts(@PathVariable Long userId) {
        List<AccountDTOResponse> accountDTOResponseList = accountService.getAllUserAccounts(userId);
        return new ResponseEntity<>(accountDTOResponseList, HttpStatus.OK);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<AccountDTOResponse> getAccountById(@PathVariable Long userId, @PathVariable Long accountId) {
        AccountDTOResponse accountDTOResponse = accountService.getAccountById(accountId, userId);
        return new ResponseEntity<>(accountDTOResponse, HttpStatus.OK);
    }

    @PutMapping("/{accountId}")
    public ResponseEntity<AccountDTOResponse> updateAccount(@RequestBody @Nullable String accountName, @Nullable String bankName, @Nullable String currency, @PathVariable Long userId, @PathVariable Long accountId) {
        AccountDTOResponse accountDTOResponse = accountService.updateAccount(accountName,bankName,currency,userId,accountId);
        return new ResponseEntity<>(accountDTOResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{accountId}")
    public ResponseEntity<MessageDTOResponse> deleteAccount(@PathVariable Long userId, @PathVariable Long accountId) {
        MessageDTOResponse messageDTOResponse = accountService.deleteAccount(userId,accountId);
        return new ResponseEntity<>(messageDTOResponse, HttpStatus.OK);
    }
}
