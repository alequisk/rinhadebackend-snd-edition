package com.alequisk.rinhav2.controllers;

import com.alequisk.rinhav2.dtos.transactions.ResumeResponse;
import com.alequisk.rinhav2.dtos.transactions.TransactionRequest;
import com.alequisk.rinhav2.dtos.transactions.TransactionResponse;
import com.alequisk.rinhav2.services.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
public class ClientController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/{clientId}/transacoes")
    public ResponseEntity<TransactionResponse> createTransaction(@PathVariable Long clientId, @RequestBody @Valid TransactionRequest request) {
        return ResponseEntity.ok(transactionService.createTransaction(request, clientId));
    }

    @GetMapping("/{clientId}/extrato")
    public ResponseEntity<ResumeResponse> getResume(@PathVariable Long clientId) {
        return ResponseEntity.ok(transactionService.getResume(clientId));
    }
}
