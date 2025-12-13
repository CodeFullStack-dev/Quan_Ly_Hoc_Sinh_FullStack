package com.example.Quan_Ly_Hoc_Sinh_Backend.dto.LoginDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AuthResponse {
    private String token; // JWT Token
    private Integer userId;
    private String username;
    private String fullName;
    private List<String> roles; // Danh sách các vai trò
}
