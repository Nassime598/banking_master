package com.banking.infrastructure.adapters;

import com.banking.application.dto.AccountDTO;
import com.banking.application.service.AccountService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@AllArgsConstructor
@RestController
@RequestMapping("/api/accounts")
@SecurityRequirement(name = "bearerAuth")
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AccountDTO> createAccount(@RequestBody AccountDTO accountDTO) {
        AccountDTO createdAccount = accountService.createAccount(accountDTO);
        return ResponseEntity.ok(createdAccount);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<AccountDTO> getAccountById(@PathVariable Long id) {
        AccountDTO account = accountService.findAccountById(id);
        return ResponseEntity.ok(account);
    }

    @GetMapping("/accountNumber/{accountNumber}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<AccountDTO> getAccountByAccountNumber(@PathVariable String accountNumber) {
        AccountDTO account = accountService.findAccountByAccountNumber(accountNumber);
        return ResponseEntity.ok(account);
    }

    @PutMapping("/{id}/balance")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> updateAccountBalance(@PathVariable Long id, @RequestParam BigDecimal amount, @RequestParam String transactionType) {
        accountService.updateAccountBalance(id, amount, transactionType);
        return ResponseEntity.noContent().build();
    }
}
