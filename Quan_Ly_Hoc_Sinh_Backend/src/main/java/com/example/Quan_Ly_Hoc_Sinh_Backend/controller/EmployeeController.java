package com.example.Quan_Ly_Hoc_Sinh_Backend.controller;

import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.EmployeeDTOs.EmployeeRequest;
import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.EmployeeDTOs.EmployeeResponse;
import com.example.Quan_Ly_Hoc_Sinh_Backend.service.Employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/register")
    public ResponseEntity<EmployeeResponse> register(@RequestBody EmployeeRequest request) {
        return ResponseEntity.ok(employeeService.register(request));
    }
}
