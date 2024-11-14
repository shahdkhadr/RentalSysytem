package com.rental.rental.controller;

import com.rental.rental.dto.BranchDTO;
import com.rental.rental.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/car-rental-system")
public class BranchController {

    @Autowired
    private BranchService branchService;

    @GetMapping("/branches")
    @PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN')")
    public ResponseEntity<List<BranchDTO>> getBranches() {
        return ResponseEntity.ok(branchService.getAllBranches());
    }

    @GetMapping("/branch/{branchId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<BranchDTO> getBranch(@PathVariable int branchId) {
        return ResponseEntity.ok(branchService.getBranchById(branchId));
    }

    @PostMapping("/branch")
    @PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN')")
    public ResponseEntity<BranchDTO> saveBranch(@RequestBody BranchDTO branchDTO) {
        return ResponseEntity.ok(branchService.saveBranch(branchDTO));
    }

    @PutMapping("/branch/{branchId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN')")
    public ResponseEntity<BranchDTO> updateBranch(@PathVariable int branchId, @RequestBody BranchDTO branchDTO) {
        return ResponseEntity.ok(branchService.updateBranch(branchId, branchDTO));
    }

    @DeleteMapping("/branch/{branchId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN')")
    public ResponseEntity<String> deleteBranch(@PathVariable int branchId) {
        branchService.deleteBranch(branchId);
        return ResponseEntity.ok("Branch with ID " + branchId + " has been deleted successfully.");
    }
}
