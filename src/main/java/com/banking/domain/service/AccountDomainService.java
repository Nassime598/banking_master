package com.banking.domain.service;

import com.banking.application.dto.AccountDTO;
import com.banking.domain.model.Account;

import java.math.BigDecimal;

public interface AccountDomainService {
    AccountDTO createAccount(AccountDTO account);
    AccountDTO findAccountById(Long id);
    AccountDTO findAccountByAccountNumber(String accountNumber);
    void updateAccountBalance(Long accountId, BigDecimal amount, String transactionType); // DEBIT or CREDIT
}
