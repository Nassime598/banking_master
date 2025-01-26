package com.banking.infrastructure.repository;

import com.banking.domain.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JpaTransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByAccountId(Long accountId);

    @Query("SELECT t FROM Transaction t WHERE t.type = :type")
    List<Transaction> findAllByType(@Param("type") String type);

}
