package com.finance.personal.controller;

import com.finance.personal.dto.request.TransactionDTORequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("/transactions")
public class TransactionController {

    @PostMapping
    public ResponseEntity<?> createNewTransaction(@RequestBody @Valid TransactionDTORequest request){
        return new ResponseEntity<>(request,HttpStatus.CREATED);
    }
}
