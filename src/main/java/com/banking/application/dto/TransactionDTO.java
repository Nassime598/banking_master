package com.banking.application.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class TransactionDTO {
    private Long id;                 // Transaction ID
    private BigDecimal amount;       // Transaction amount
    private String type;             // Type of transaction: "DEBIT" or "CREDIT"
    private Long accountId;          // ID of the associated account
    private LocalDateTime timestamp; // Time of the transaction
}
