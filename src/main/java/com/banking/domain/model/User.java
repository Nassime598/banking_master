package com.banking.domain.model;

import com.banking.application.dto.UserDTO;
import com.banking.application.dto.Roles;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Roles role;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Account> accounts;

    public UserDTO toDTO() {
        UserDTO dto = new UserDTO();
        dto.setUsername(this.username);
        dto.setRole(this.role);
        return dto;
    }

    public static User fromDTO(UserDTO dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());
        return user;
    }
}
