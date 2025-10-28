package com.finance.personal.exception;

public class DuplicatedItemException extends RuntimeException {
    public DuplicatedItemException(String message) {
        super(message);
    }
}