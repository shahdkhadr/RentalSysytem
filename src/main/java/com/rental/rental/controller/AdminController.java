package com.rental.rental.controller;

import com.rental.rental.dto.AdminDTO;
import com.rental.rental.service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/car-rental-system")
@Tag(name = "Admin", description = "The Admin API")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/admins")
    @Operation(summary = "Get all admins", description = "Retrieve a list of all admins")
    public ResponseEntity<List<AdminDTO>> getAdmins(){
        return ResponseEntity.ok(adminService.getAllAdmins());
    }

    @GetMapping("/admin/{adminId}")
    @Operation(summary = "Get admin by ID", description = "Retrieve an admin by their ID")
    public ResponseEntity<AdminDTO> getAdmin(@PathVariable int adminId){
        return ResponseEntity.ok(adminService.getAdminById(adminId));
    }

    @PostMapping("/admin")
    @Operation(summary = "Create a new admin", description = "Add a new admin to the system")
    public ResponseEntity<AdminDTO> saveAdmin(@RequestBody AdminDTO adminDTO){
        return ResponseEntity.ok(adminService.saveAdmin(adminDTO));
    }

    @PutMapping("/admin/{adminId}")
    @Operation(summary = "Update a admin", description = "Update details of an existing admin")
    public ResponseEntity<AdminDTO> updateAdmin(@PathVariable int adminId, @RequestBody AdminDTO adminDTO){
        return ResponseEntity.ok(adminService.updateAdmin(adminId ,adminDTO));
    }

    @DeleteMapping("/admin/{adminId}")
    @Operation(summary = "Delete a admin", description = "Remove a admin from the system")
    public void deleteAdmin(@PathVariable int adminId){
         adminService.deleteAdmin(adminId);
    }

}
