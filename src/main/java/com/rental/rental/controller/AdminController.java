package com.rental.rental.controller;

import com.rental.rental.dto.AdminDTO;
import com.rental.rental.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/car-rental-system")
public class AdminController {

    private final AdminService adminService;
    public AdminController( AdminService adminService ){
        this.adminService = adminService;
    }

    @GetMapping("/admins")
    public ResponseEntity<List<AdminDTO>> getAdmins(){
        return ResponseEntity.ok(adminService.getAllAdmins());
    }

    @GetMapping("/admin")
    public ResponseEntity<AdminDTO> getAdmin(@RequestParam int adminId){
        return ResponseEntity.ok(adminService.getAdminById(adminId));
    }

    @PostMapping("/admin")
    public ResponseEntity<AdminDTO> saveAdmin(@RequestBody AdminDTO adminDTO){
        return ResponseEntity.ok(adminService.saveAdmin(adminDTO));
    }

    @PostMapping("/update-admin")
    public ResponseEntity<AdminDTO> updateAdmin(@RequestBody AdminDTO adminDTO){
        return ResponseEntity.ok(adminService.saveAdmin(adminDTO));
    }

    @DeleteMapping("/admin")
    public void deleteAdmin(@RequestParam int adminId){
         adminService.deleteAdmin(adminId);
    }

}
