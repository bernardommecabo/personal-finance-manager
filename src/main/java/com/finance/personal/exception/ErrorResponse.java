package com.finance.personal.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    private String message;
    private List<String> details;

    public List<String> getDetails() {
        if (details == null) {
            details = new ArrayList<>();
        }
        return details;
    }

    public void setDetails(List<String> details) {
        this.details = details;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
