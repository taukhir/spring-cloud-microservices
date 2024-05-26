package org.ahmed.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.ahmed.dto.RoleDTO;
import org.ahmed.model.Role;
import org.ahmed.service.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/role")
@AllArgsConstructor
@Tag(name = "Role Management")
public class RoleController {

    private final RoleService roleService;

    @GetMapping
    @Operation(summary = "List Role")
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    @GetMapping("/find/{roleName}")
    @Operation(summary = "Get Role by ID")
    public Role getRoleById(@PathVariable String roleName) {
        return roleService.getRoleByName(roleName);
    }

    @PostMapping("/create")
    @Operation(summary = "Create Role")
    public ResponseEntity<Role> createRole(@Valid @RequestBody RoleDTO role) {
        Role createdRole = roleService.createRole(role);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRole);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Update Role")
    public Role updateRole(@PathVariable Long id, @Valid @RequestBody RoleDTO roleDetails) {
        return roleService.updateRole(roleDetails);
    }


}
