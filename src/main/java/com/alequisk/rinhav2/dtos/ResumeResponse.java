package com.alequisk.rinhav2.dtos;

import com.alequisk.rinhav2.domain.Transaction;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

public record ResumeResponse(
    Saldo saldo,
    List<Transacao> ultimas_transacoes
) {

    record Saldo(
            BigInteger total,
            LocalDateTime data_extrato,
            BigInteger limite
    ) {}

    record Transacao(
            BigInteger valor,
            Character tipo,
            String descricao,
            LocalDateTime realizada_em
    ) {
        public static Transacao from(Transaction transaction) {
            return new Transacao(transaction.getValue(), transaction.getType(), transaction.getDescription(), transaction.getCreatedAt());
        }

        public static List<Transacao> from(List<Transaction> transactions) {
            return transactions.stream().map(Transacao::from).toList();
        }
    }

    public static ResumeResponse from(BigInteger total, LocalDateTime dataExtrato, BigInteger limite, List<Transaction> ultimasTransacoes) {
        Saldo saldo = new Saldo(total, dataExtrato, limite);
        return new ResumeResponse(saldo, Transacao.from(ultimasTransacoes));
    }
}