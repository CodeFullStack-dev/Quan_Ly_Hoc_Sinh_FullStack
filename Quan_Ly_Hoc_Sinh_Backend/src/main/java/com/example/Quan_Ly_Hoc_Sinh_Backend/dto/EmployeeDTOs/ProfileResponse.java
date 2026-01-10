package com.example.Quan_Ly_Hoc_Sinh_Backend.dto.EmployeeDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileResponse {
    private Long id;
    private String username;
    private String fullName;
    private String email;
    private Set<String> roles;
}
