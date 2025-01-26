package com.banking.domain.service;

import com.banking.application.dto.TransactionDTO;
import com.banking.domain.model.Transaction;

import java.util.List;

public interface TransactionDomainService {
    TransactionDTO createTransaction(TransactionDTO transaction);
    List<TransactionDTO> findTransactionsByAccountId(Long accountId);
}
