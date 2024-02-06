package com.alequisk.rinhav2.dtos.transactions;

import jakarta.validation.constraints.NotNull;

import java.math.BigInteger;

public record TransactionResponse(
        @NotNull BigInteger limite,
        @NotNull BigInteger saldo
) {
}
