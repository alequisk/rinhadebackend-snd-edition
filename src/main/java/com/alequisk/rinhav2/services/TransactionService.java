package com.alequisk.rinhav2.services;

import com.alequisk.rinhav2.domain.Client;
import com.alequisk.rinhav2.domain.Transaction;
import com.alequisk.rinhav2.dtos.transactions.ResumeResponse;
import com.alequisk.rinhav2.dtos.transactions.TransactionRequest;
import com.alequisk.rinhav2.dtos.transactions.TransactionResponse;
import com.alequisk.rinhav2.exceptions.ClientNotFoundException;
import com.alequisk.rinhav2.repositories.ClientsRepository;
import com.alequisk.rinhav2.repositories.TransactionsRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TransactionService {

    private final TransactionsRepository transactionsRepository;
    private final ClientsRepository clientsRepository;

    public TransactionService(TransactionsRepository transactionsRepository, ClientsRepository clientsRepository) {
        this.transactionsRepository = transactionsRepository;
        this.clientsRepository = clientsRepository;
    }

    public TransactionResponse createTransaction(TransactionRequest request, Long clientId) {
        Client client = clientsRepository.findById(clientId).orElseThrow(() -> new ClientNotFoundException(clientId));
        Transaction transaction = new Transaction();
        request.mapToTransaction(transaction);
        client.addTransaction(transaction);
        clientsRepository.save(client);
        return new TransactionResponse(client.getLimit(), client.getBalance());
    }

    public ResumeResponse getResume(Long clientId) {
        Client client = clientsRepository.findById(clientId).orElseThrow(() -> new ClientNotFoundException(clientId));
        return ResumeResponse.from(client.getBalance(), LocalDateTime.now(), client.getLimit(), transactionsRepository.findLastTenTransactionsOf(client));
    }
}
