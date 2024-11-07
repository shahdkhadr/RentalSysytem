package com.rental.rental.controller;


import com.rental.rental.dto.BranchDTO;
import com.rental.rental.service.BranchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/car-rental-system")
@Tag(name = "Branch", description = "The Branch API")
public class BranchController {

    @Autowired
    private BranchService branchService;

    @GetMapping("/branches")
    @Operation(summary = "Get all branches", description = "Retrieve a list of all branches")
    public ResponseEntity<List<BranchDTO>> getBranches(){
        return ResponseEntity.ok(branchService.getAllBranches());
    }

    @GetMapping("/branch/{branchId}")
    @Operation(summary = "Get branch by ID", description = "Retrieve an branch by their ID")
    public ResponseEntity<BranchDTO> getBranch(@PathVariable int branchId){
        return ResponseEntity.ok(branchService.getBranchById(branchId));
    }

    @PostMapping("/branch")
    @Operation(summary = "Create a new branch", description = "Add a new branch to the system")
    public ResponseEntity<BranchDTO> saveBranch(@RequestBody BranchDTO branchDTO){
        return ResponseEntity.ok(branchService.saveBranch(branchDTO));
    }

    @PutMapping("/branch/{branchId}")
    @Operation(summary = "Update a branch", description = "Update details of an existing branch")
    public ResponseEntity<BranchDTO> updateBranch(@PathVariable int branchId, @RequestBody BranchDTO branchDTO){
        return ResponseEntity.ok(branchService.updateBranch(branchId ,branchDTO));
    }

    @DeleteMapping("/branch/{branchId}")
    @Operation(summary = "Delete a branch", description = "Remove a branch from the system")
    public void deleteBranch(@PathVariable int branchId){
        branchService.deleteBranch(branchId);
    }

}
