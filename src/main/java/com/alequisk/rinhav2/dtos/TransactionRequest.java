package com.alequisk.rinhav2.dtos;

import com.alequisk.rinhav2.domain.Transaction;
import jakarta.validation.constraints.NotNull;

import java.math.BigInteger;

public record TransactionRequest(
        @NotNull BigInteger valor,
        @NotNull Character tipo,
        @NotNull String descricao
) {
    public void mapToTransaction(Transaction transaction) {
        transaction.setValue(valor);
        transaction.setType(tipo);
        transaction.setDescription(descricao);
    }
}
