package com.example.Quan_Ly_Hoc_Sinh_Backend.mapper;


import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.EmployeeDTOs.EmployeeRequest;
import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.EmployeeDTOs.EmployeeResponse;
import com.example.Quan_Ly_Hoc_Sinh_Backend.model.Entity.Employee;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class EmployeeMapper {
    public EmployeeResponse toResponse(Employee employee) {
        if (employee == null) return null;
        EmployeeResponse response = new EmployeeResponse();
        response.setId(employee.getId());
        response.setUsername(employee.getUsername());
        response.setFullName(employee.getFullName());
        response.setEmail(employee.getEmail());
        response.setRoles(employee.getRoles().stream()
                .map(role -> role.getName().name())
                .collect(Collectors.toSet()));
        return response;
    }

    public Employee toEntity(EmployeeRequest request) {
        if (request == null) return null;
        Employee employee = new Employee();
        employee.setUsername(request.getUsername());
        employee.setPassword(request.getPassword()); // Sẽ được mã hóa ở Service
        employee.setFullName(request.getFullName());
        employee.setEmail(request.getEmail());
        employee.setPhoneNumber(request.getPhoneNumber());
        return employee;
    }
}
