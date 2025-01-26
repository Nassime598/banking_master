package com.banking.application.service;

import com.banking.application.dto.UserDTO;
import com.banking.domain.model.User;
import com.banking.domain.repository.UserRepository;
import com.banking.domain.service.UserDomainService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDomainService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = User.fromDTO(userDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user).toDTO();
    }

    @Override
    public UserDTO findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"))
                .toDTO();
    }

    @Override
    public UserDTO findUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"))
                .toDTO();
    }
}
