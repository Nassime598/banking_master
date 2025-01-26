package com.banking.domain.model;

import com.banking.application.dto.AccountDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "accounts")
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String accountNumber;

    @Column(nullable = false)
    private BigDecimal balance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User owner;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Transaction> transactions;

    public AccountDTO toDTO() {
        AccountDTO dto = new AccountDTO();
        dto.setId(this.id);
        dto.setAccountNumber(this.accountNumber);
        dto.setBalance(this.balance);
        dto.setUserId(this.owner.getId());
        return dto;
    }

    public static Account fromDTO(AccountDTO dto, User owner) {
        Account account = new Account();
        account.setAccountNumber(dto.getAccountNumber());
        account.setBalance(dto.getBalance());
        account.setOwner(owner);
        return account;
    }
}
