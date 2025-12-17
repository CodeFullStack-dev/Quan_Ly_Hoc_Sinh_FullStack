package com.example.Quan_Ly_Hoc_Sinh_Backend.service.Employee;

import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.EmployeeDTOs.EmployeeRequest;
import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.EmployeeDTOs.EmployeeResponse;

public interface EmployeeService {
    EmployeeResponse register(EmployeeRequest request);
}
