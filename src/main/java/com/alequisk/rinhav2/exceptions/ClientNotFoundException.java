package com.alequisk.rinhav2.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ClientNotFoundException extends RuntimeException {
    public ClientNotFoundException(Long id) {
        super("Client com id " + id + " não encontrado");
    }
}
