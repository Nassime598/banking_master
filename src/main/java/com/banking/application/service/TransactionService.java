package com.banking.application.service;

import com.banking.application.dto.TransactionDTO;
import com.banking.domain.model.Account;
import com.banking.domain.model.Transaction;
import com.banking.domain.repository.AccountRepository;
import com.banking.domain.repository.TransactionRepository;
import com.banking.domain.service.TransactionDomainService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService implements TransactionDomainService {
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    public TransactionService(TransactionRepository transactionRepository, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    public TransactionDTO createTransaction(TransactionDTO transactionDTO) {
        Account account = accountRepository.findById(transactionDTO.getAccountId())
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));
        Transaction transaction = Transaction.fromDTO(transactionDTO, account);
        return transactionRepository.save(transaction).toDTO();
    }

    public List<TransactionDTO> findTransactionsByAccountId(Long accountId) {
        return transactionRepository.findByAccountId(accountId).stream()
                .map(Transaction::toDTO)
                .toList();
    }
}

