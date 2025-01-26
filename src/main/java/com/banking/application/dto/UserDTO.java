package com.banking.application.dto;

import lombok.Data;

@Data
public class UserDTO {
    private String username;
    private String password;
    private Roles role; // e.g., "ADMIN", "USER"
}
