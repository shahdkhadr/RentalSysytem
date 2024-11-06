package com.rental.rental.controller;

import com.rental.rental.dto.AdminDTO;
import com.rental.rental.service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/car-rental-system")
@Tag(name = "Admin", description = "The Admin API")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/admins")
    @Operation(summary = "Get all admins", description = "Retrieve a list of all admins")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public ResponseEntity<List<AdminDTO>> getAdmins() {
        List<AdminDTO> admins = adminService.getAllAdmins();
        return ResponseEntity.ok(admins);
    }

    @GetMapping("/admin/{adminId}")
    @Operation(summary = "Get admin by ID", description = "Retrieve an admin by their ID")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public ResponseEntity<AdminDTO> getAdmin(@PathVariable int adminId) {
        AdminDTO admin = adminService.getAdminById(adminId);
        return ResponseEntity.ok(admin);
    }

    @PostMapping("/admin")
    @Operation(summary = "Create a new admin", description = "Add a new admin to the system")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public ResponseEntity<AdminDTO> saveAdmin(@RequestBody AdminDTO adminDTO) {
        AdminDTO savedAdmin = adminService.saveAdmin(adminDTO);
        return ResponseEntity.ok(savedAdmin);
    }

    @PutMapping("/admin/{adminId}")
    @Operation(summary = "Update an admin", description = "Update details of an existing admin")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public ResponseEntity<AdminDTO> updateAdmin(@PathVariable int adminId, @RequestBody AdminDTO adminDTO) {
        AdminDTO updatedAdmin = adminService.updateAdmin(adminId, adminDTO);
        return ResponseEntity.ok(updatedAdmin);
    }

    @DeleteMapping("/admin/{adminId}")
    @Operation(summary = "Delete an admin", description = "Remove an admin from the system")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public ResponseEntity<Void> deleteAdmin(@PathVariable int adminId) {
        adminService.deleteAdmin(adminId);
        return ResponseEntity.noContent().build();
    }
}
