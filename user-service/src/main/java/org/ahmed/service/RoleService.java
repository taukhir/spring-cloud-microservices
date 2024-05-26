package org.ahmed.service;

import lombok.RequiredArgsConstructor;
import org.ahmed.dto.RoleDTO;
import org.ahmed.model.Role;
import org.ahmed.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Role getRoleByName(String roleName) {
        return roleRepository.findByRoleName(roleName);
    }

    public Role createRole(RoleDTO roleDTO) {
        return roleRepository.save(Role.builder().roleName(roleDTO.getRoleName()).build());
    }

    public Role updateRole(RoleDTO roleDTO) {
        return roleRepository.save(roleRepository.findByRoleName(roleDTO.getRoleName()));
    }
}
