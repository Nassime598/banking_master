package com.banking.application.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountDTO {
    private Long id;              // Unique account ID
    private String accountNumber;  // Unique account identifier
    private BigDecimal balance;   // Current account balance
    private Long userId;          // ID of the user who owns this account
}
