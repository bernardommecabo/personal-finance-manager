package com.finance.personal.controller;

import com.finance.personal.dto.request.TransactionDTORequest;
import com.finance.personal.dto.response.MessageDTOResponse;
import com.finance.personal.dto.response.TransactionDTOResponse;
import com.finance.personal.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("/user/{userId}/account/{accountId}/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    @Operation(summary = "Create a new transaction", description = "Create a new transaction for an account")
    public ResponseEntity<?> createNewTransaction(@PathVariable Long accountId,@PathVariable Long userId, @RequestBody @Valid TransactionDTORequest request){
        TransactionDTOResponse transactionDTOResponse = transactionService.createNewTransaction(request,accountId,userId);
        return new ResponseEntity<>(transactionDTOResponse,HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get transaction by ID", description = "Get a transaction by its ID")
    public ResponseEntity<TransactionDTOResponse> getTransactionById(@PathVariable Long id) {
        TransactionDTOResponse transactionDTOResponse = transactionService.getTransactionById(id);
        return new ResponseEntity<>(transactionDTOResponse, HttpStatus.OK);
    }

    @GetMapping
    @Operation(summary = "Get all transactions", description = "Get a list of all transactions")
    public ResponseEntity<List<TransactionDTOResponse>> getAllTransactionsByAccountId(@PathVariable Long accountId){
        List<TransactionDTOResponse> transactionDTOResponses = transactionService.getAllTransactions(accountId);
        return new ResponseEntity<>(transactionDTOResponses, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update transaction", description = "Update a transaction by its ID")
    public ResponseEntity<TransactionDTOResponse> updateTransaction(@PathVariable Long accountId, @PathVariable Long id, @RequestBody @Valid TransactionDTORequest request){
        TransactionDTOResponse transactionDTOResponse = transactionService.updateTransaction(id,request,accountId);
        return new ResponseEntity<>(transactionDTOResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete transaction", description = "Delete a transaction by its ID")
    public ResponseEntity<MessageDTOResponse> deleteTransaction(@PathVariable Long id) {
        MessageDTOResponse messageDTOResponse = transactionService.deleteTransaction(id);
        return new ResponseEntity<>(messageDTOResponse, HttpStatus.OK);
    }
}
