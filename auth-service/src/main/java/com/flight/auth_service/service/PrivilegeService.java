package com.flight.auth_service.service;

import org.springframework.stereotype.Service;

import com.flight.auth_service.entity.Privilege;
import com.flight.auth_service.repository.PrivilegeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PrivilegeService {

    private final PrivilegeRepository privilegeRepository;

    public Privilege createPrivilege(String privilegeName) {

        Privilege privilege = new Privilege();
        privilege.setPrivilegeName(privilegeName);

        return privilegeRepository.save(privilege);
    }
}
