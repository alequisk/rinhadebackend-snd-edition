package com.alequisk.rinhav2.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class InsufficientCreditTransactionException extends RuntimeException {
    public InsufficientCreditTransactionException(String message) {
        super(message);
    }
    public InsufficientCreditTransactionException() {
        super("Limite de saldo excedido");
    }
}
