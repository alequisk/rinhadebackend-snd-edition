package com.alequisk.rinhav2.domain;

import com.alequisk.rinhav2.constants.TransactionConstants;
import com.alequisk.rinhav2.exceptions.InsufficientCreditTransactionException;
import jakarta.persistence.*;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "clients")
public class Client {
    @Id
    private Long id;

    @Column(nullable = false, name = "credit_limit")
    private BigInteger limit;

    @Column(nullable = false)
    private BigInteger balance;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client", cascade = CascadeType.MERGE)
    Set<Transaction> transactions = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigInteger getLimit() {
        return limit;
    }

    public void setLimit(BigInteger limit) {
        this.limit = limit;
    }

    public BigInteger getBalance() {
        return balance;
    }

    public void setBalance(BigInteger balance) {
        this.balance = balance;
    }

    public void addTransaction(Transaction transaction) throws InsufficientCreditTransactionException {
        var newBalance = this.balance;
        if (transaction.getType().equals(TransactionConstants.DEBIT)) {
            newBalance = this.balance.subtract(transaction.getValue());
            if (newBalance.compareTo(this.limit.negate()) < 0) {
                throw new InsufficientCreditTransactionException();
            }
        }
        if (transaction.getType().equals(TransactionConstants.CREDIT)) {
            newBalance = this.balance.add(transaction.getValue());
        }
        this.balance = newBalance;
        transaction.setClient(this);
        this.transactions.add(transaction);
    }
}
