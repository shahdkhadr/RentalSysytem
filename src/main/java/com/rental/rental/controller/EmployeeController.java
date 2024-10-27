package com.rental.rental.controller;

import com.rental.rental.dto.DepartmentDTO;
import com.rental.rental.dto.EmployeeDTO;
import com.rental.rental.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/car-rental-system")
@Tag(name = "Employee", description = "The Employee API")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    @Operation(summary = "Get all employees", description = "Retrieve a list of all employees")
    public ResponseEntity<List<EmployeeDTO>> getEmployees(){
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @GetMapping("/employee/{employeeId}")
    @Operation(summary = "Get employee by ID", description = "Retrieve an employee by their ID")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable int employeeId){
        return ResponseEntity.ok(employeeService.getEmployeeById(employeeId));
    }

    @PostMapping("/employee")
    @Operation(summary = "Create a new employee", description = "Add a new employee to the system")
    public ResponseEntity<EmployeeDTO> saveEmployee(@RequestBody EmployeeDTO employeeDTO){
        return ResponseEntity.ok(employeeService.saveEmployee(employeeDTO));
    }

    @PutMapping("/employee/{employeeId}")
    @Operation(summary = "Update an employee", description = "Update details of an existing employee")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable int employeeId , @RequestBody EmployeeDTO employeeDTO){
        return ResponseEntity.ok(employeeService.updateEmployee( employeeId, employeeDTO));
    }

    @DeleteMapping("/employee/{employeeId}")
    @Operation(summary = "Delete an employee", description = "Remove an employee from the system")
    public void deleteEmployee(@PathVariable int employeeId){
        employeeService.deleteEmployee(employeeId);
    }
}
