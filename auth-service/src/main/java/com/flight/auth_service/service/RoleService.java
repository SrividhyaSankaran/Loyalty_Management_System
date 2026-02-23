package com.flight.auth_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.flight.auth_service.entity.Privilege;
import com.flight.auth_service.entity.Role;
import com.flight.auth_service.entity.RolePrivilege;
import com.flight.auth_service.repository.PrivilegeRepository;
import com.flight.auth_service.repository.RolePrivilegeRepository;
import com.flight.auth_service.repository.RoleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;
    private final PrivilegeRepository privilegeRepository;
    private final RolePrivilegeRepository rolePrivilegeRepository;

    public Role createRole(String roleName) {

        Role role = new Role();
        role.setRoleName(roleName);
        return roleRepository.save(role);
    }

    public void assignPrivileges(Long roleId,
                                 List<Long> privilegeIds) {

        Role role = roleRepository.findById(roleId)
                .orElseThrow();

        for (Long pid : privilegeIds) {

            Privilege privilege =
                    privilegeRepository.findById(pid)
                            .orElseThrow();

            rolePrivilegeRepository.save(
                    new RolePrivilege(null, role, privilege)
            );
        }
    }
}
