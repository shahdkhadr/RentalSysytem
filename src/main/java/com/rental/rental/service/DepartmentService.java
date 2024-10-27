package com.rental.rental.service;

import com.rental.rental.dto.DepartmentDTO;
import com.rental.rental.model.Department;
import com.rental.rental.model.Employee;
import com.rental.rental.repository.DepartmentRepository;
import com.rental.rental.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<DepartmentDTO> getAllDepartments(){
        return departmentRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public DepartmentDTO getDepartmentById( int departmentId ){
        return departmentRepository.findById(departmentId).map(this::convertToDTO).orElseThrow(
                () -> new RuntimeException("Department not found")
        );
    }

    public DepartmentDTO saveDepartment(DepartmentDTO departmentDTO){
        Department department = convertToEntity(departmentDTO);
        Department savedDepartment = departmentRepository.save(department);
        return convertToDTO(savedDepartment);
    }

    public DepartmentDTO updateDepartment(int id, DepartmentDTO departmentDTO) {
        Optional<Department> existingDepartmentOpt = departmentRepository.findById(id);
        if (existingDepartmentOpt.isPresent()) {
            Department existingDepartment = existingDepartmentOpt.get();

            if (departmentDTO.getDepartmentName() != null && !departmentDTO.getDepartmentName().isEmpty()) {
                existingDepartment.setDepartmentName(departmentDTO.getDepartmentName());
            }

            if (departmentDTO.getDepartmentPosition() != null && !departmentDTO.getDepartmentPosition().isEmpty()) {
                existingDepartment.setDepartmentPosition(departmentDTO.getDepartmentPosition());
            }

            Department updatedDepartment = departmentRepository.save(existingDepartment);
            return convertToDTO(updatedDepartment);
        }
        return null;
    }


    public void deleteDepartment( int departmentId ){
        departmentRepository.deleteById(departmentId);
    }


    private DepartmentDTO convertToDTO(Department department) {
        return new DepartmentDTO(
                department.getDepartmentId(),
                department.getDepartmentName(),
                department.getDepartmentPosition()
        );
    }

    private Department convertToEntity(DepartmentDTO departmentDTO) {
        return Department.builder()
                .departmentId(departmentDTO.getDepartmentId())
                .departmentName(departmentDTO.getDepartmentName())
                .departmentPosition(departmentDTO.getDepartmentPosition())
                .build();
    }

}
