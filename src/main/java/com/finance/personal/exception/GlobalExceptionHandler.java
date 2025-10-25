package com.finance.personal.exception;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicatedItemException.class)
    public ResponseEntity<ErrorResponse> handleDuplicatedItemException(DuplicatedItemException exception){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("Validation Error");
        errorResponse.getDetails().add(exception.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    //Alterar a mensagem do erro (Validação)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("Validation Error");
        errorResponse.getDetails().addAll(ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList()));
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    //exception handler NotFound
}
