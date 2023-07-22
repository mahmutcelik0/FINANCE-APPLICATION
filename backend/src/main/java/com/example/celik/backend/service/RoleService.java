package com.example.celik.backend.service;

import com.example.celik.backend.model.Role;
import com.example.celik.backend.repository.RoleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Set<Role> getRolesByName(Set<Role> roles) {
        Set<Role> userRoles = new HashSet<>();

        roles.forEach(e->userRoles.add(roleRepository.findByRoleName(e.getRoleName())));

        return userRoles;
    }

    public ResponseEntity addNewRole(Role role) {
        boolean isExist = roleRepository.existsByRoleNameContains(role.getRoleName());

        if(isExist){
            return new ResponseEntity("Role already has been recorded", HttpStatus.CONFLICT);
        }

        roleRepository.save(role);

        return new ResponseEntity("New role added",HttpStatus.CREATED);
    }
}
