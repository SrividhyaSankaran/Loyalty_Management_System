package com.flight.auth_service.service;

import java.time.LocalDateTime;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.flight.auth_service.dto.UserRequest;
import com.flight.auth_service.entity.Role;
import com.flight.auth_service.entity.User;
import com.flight.auth_service.entity.UserRole;
import com.flight.auth_service.exception.RoleNotFoundException;
import com.flight.auth_service.exception.UserAlreadyExistsException;
import com.flight.auth_service.repository.RoleRepository;
import com.flight.auth_service.repository.UserRepository;
import com.flight.auth_service.repository.UserRoleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;


     public User createUser(UserRequest request) {

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new UserAlreadyExistsException(request.getUsername());
        }
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(
                passwordEncoder.encode(request.getPassword())
        );
        user.setStatus(request.getStatus());
        user.setCreatedAt(LocalDateTime.now());

        User savedUser = userRepository.save(user);

        for (Long roleId : request.getRoleIds()) {
            Role role = roleRepository.findById(roleId)
                    .orElseThrow(() -> new RoleNotFoundException(roleId));
            userRoleRepository.save(new UserRole(savedUser, role));
        }

        return savedUser;
    }
    
}
