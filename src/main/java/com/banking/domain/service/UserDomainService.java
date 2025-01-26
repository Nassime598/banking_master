package com.banking.domain.service;

import com.banking.application.dto.UserDTO;

public interface UserDomainService {
    UserDTO createUser(UserDTO user);
    UserDTO findUserById(Long id);
    UserDTO findUserByUsername(String username);
}
