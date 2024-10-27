package com.rental.rental.controller;

import com.rental.rental.dto.DepartmentDTO;
import com.rental.rental.model.Department;
import com.rental.rental.service.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/car-rental-system")
@Tag(name = "Department", description = "The Department API")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @Operation(summary = "Get all departments", description = "Retrieve a list of all departments")
    @GetMapping("/departments")
    public ResponseEntity<List<DepartmentDTO>> getDepartments(){
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }

    @GetMapping("/department/{departmentId}")
    @Operation(summary = "Get department by ID", description = "Retrieve an department by their ID")
    public ResponseEntity<DepartmentDTO> getDepartmentById(@PathVariable int departmentId){
        return ResponseEntity.ok(departmentService.getDepartmentById(departmentId));
    }

    @PostMapping("/department")
    @Operation(summary = "Create a new department", description = "Add a new department to the system")
    public ResponseEntity<DepartmentDTO> saveDepartment(@RequestBody DepartmentDTO departmentDTO){
        return ResponseEntity.ok(departmentService.saveDepartment(departmentDTO));
    }

    @PutMapping("/department/{departmentId}")
    @Operation(summary = "Update a department", description = "Update details of an existing department")
    public ResponseEntity<DepartmentDTO> updateDepartment(@PathVariable int departmentId ,@RequestBody DepartmentDTO departmentDTO){
        return ResponseEntity.ok(departmentService.updateDepartment( departmentId, departmentDTO));
    }

    @DeleteMapping("/department/{departmentId}")
    @Operation(summary = "Delete a department", description = "Remove a department from the system")
    public void deleteDepartment(@PathVariable int departmentId){
        departmentService.deleteDepartment(departmentId);
    }

}
