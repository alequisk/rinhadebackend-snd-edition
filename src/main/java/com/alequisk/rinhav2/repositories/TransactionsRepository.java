package com.alequisk.rinhav2.repositories;

import com.alequisk.rinhav2.domain.Client;
import com.alequisk.rinhav2.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionsRepository extends JpaRepository<Transaction, Long> {

    @Query("SELECT t FROM Transaction t WHERE t.client = :client ORDER BY t.createdAt DESC LIMIT 10")
    List<Transaction> findLastTenTransactionsOf(Client client);
}
