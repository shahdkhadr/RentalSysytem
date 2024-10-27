package com.rental.rental.service;

import com.rental.rental.dto.EmployeeDTO;
import com.rental.rental.model.Department;
import com.rental.rental.model.Employee;
import com.rental.rental.repository.DepartmentRepository;
import com.rental.rental.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;


    private EmployeeDTO convertToDto(Employee employee) {
        return new EmployeeDTO(
                employee.getEmployeeId(),
                employee.getEmployeeName(),
                employee.getPhoneNumber(),
                employee.getEmploymentType(),
                employee.getDepartments().getDepartmentId()
        );
    }


    private Employee convertToEntity(EmployeeDTO employeeDTO) {
        Optional<Department> department = departmentRepository.findById(employeeDTO.getDepartmentId());
        if (!department.isPresent()) {
            throw new IllegalArgumentException("Department not found");
        }
        return Employee.builder()
                .employeeId(employeeDTO.getEmployeeId())
                .employeeName(employeeDTO.getEmployeeName())
                .phoneNumber(employeeDTO.getPhoneNumber())
                .employmentType(employeeDTO.getEmploymentType())
                .departments(department.get()) // Set the department
                .build();
    }


    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
        Employee employee = convertToEntity(employeeDTO);
        Employee savedEmployee = employeeRepository.save(employee);
        return convertToDto(savedEmployee);
    }


    public EmployeeDTO getEmployeeById(int id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        return employee.map(this::convertToDto).orElse(null);
    }


    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public EmployeeDTO updateEmployee(int id, EmployeeDTO employeeDTO) {
        Optional<Employee> existingEmployeeOpt = employeeRepository.findById(id);
        if (existingEmployeeOpt.isPresent()) {
            Employee existingEmployee = existingEmployeeOpt.get();

            if (employeeDTO.getEmployeeName() != null && !employeeDTO.getEmployeeName().isEmpty()) {
                existingEmployee.setEmployeeName(employeeDTO.getEmployeeName());
            }

            if (employeeDTO.getPhoneNumber() != null && !employeeDTO.getPhoneNumber().isEmpty()) {
                existingEmployee.setPhoneNumber(employeeDTO.getPhoneNumber());
            }

            if (employeeDTO.getEmploymentType() != null && !employeeDTO.getEmploymentType().isEmpty()) {
                existingEmployee.setEmploymentType(employeeDTO.getEmploymentType());
            }

            // If departmentId is provided, update the department
            if (employeeDTO.getDepartmentId() != 0) {
                Optional<Department> department = departmentRepository.findById(employeeDTO.getDepartmentId());
                department.ifPresent(existingEmployee::setDepartments);
            }

            Employee updatedEmployee = employeeRepository.save(existingEmployee);
            return convertToDto(updatedEmployee);
        }
        return null;
    }

    public void deleteEmployee(int id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            employeeRepository.deleteById(id);
        }
    }
}
