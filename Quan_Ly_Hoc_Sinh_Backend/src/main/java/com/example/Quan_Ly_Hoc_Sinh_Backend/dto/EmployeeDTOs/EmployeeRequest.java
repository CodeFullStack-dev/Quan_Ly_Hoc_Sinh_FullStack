package com.example.Quan_Ly_Hoc_Sinh_Backend.dto.EmployeeDTOs;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequest {
    private String username;
    private String password;
    private String fullName;
    private String email;
    private String phoneNumber;
    private Long schoolId;
    private Set<String> roles;
}
