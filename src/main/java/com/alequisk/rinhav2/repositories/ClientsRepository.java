package com.alequisk.rinhav2.repositories;

import com.alequisk.rinhav2.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientsRepository extends JpaRepository<Client, Long> {
}
