package com.banking.application.service;

import com.banking.application.dto.AccountDTO;
import com.banking.domain.model.Account;
import com.banking.domain.model.User;
import com.banking.domain.repository.AccountRepository;
import com.banking.domain.repository.UserRepository;
import com.banking.domain.service.AccountDomainService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountService implements AccountDomainService {
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    public AccountService(AccountRepository accountRepository, UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }

    @Override
    public AccountDTO createAccount(AccountDTO accountDTO) {
        User owner = userRepository.findById(accountDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Account account = Account.fromDTO(accountDTO, owner);
        return accountRepository.save(account).toDTO();
    }

    @Override
    public AccountDTO findAccountById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Account not found"))
                .toDTO();
    }

    @Override
    public AccountDTO findAccountByAccountNumber(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new IllegalArgumentException("Account not found"))
                .toDTO();
    }

    @Override
    public void updateAccountBalance(Long accountId, BigDecimal amount, String transactionType) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));

        if ("DEBIT".equalsIgnoreCase(transactionType)) {
            account.setBalance(account.getBalance().subtract(amount));
        } else if ("CREDIT".equalsIgnoreCase(transactionType)) {
            account.setBalance(account.getBalance().add(amount));
        }
        accountRepository.save(account);
    }
}
