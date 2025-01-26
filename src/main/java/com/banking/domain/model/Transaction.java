package com.banking.domain.model;

import com.banking.application.dto.TransactionDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private String type; // "DEBIT" or "CREDIT"

    @Column(nullable = false)
    private LocalDateTime timestamp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    public TransactionDTO toDTO() {
        TransactionDTO dto = new TransactionDTO();
        dto.setId(this.id);
        dto.setAmount(this.amount);
        dto.setType(this.type);
        dto.setAccountId(this.account.getId());
        dto.setTimestamp(this.timestamp);
        return dto;
    }

    public static Transaction fromDTO(TransactionDTO dto, Account account) {
        Transaction transaction = new Transaction();
        transaction.setAmount(dto.getAmount());
        transaction.setType(dto.getType());
        transaction.setTimestamp(dto.getTimestamp());
        transaction.setAccount(account);
        return transaction;
    }
}
