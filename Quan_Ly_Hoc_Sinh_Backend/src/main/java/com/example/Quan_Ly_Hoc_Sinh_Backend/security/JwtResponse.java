package com.example.Quan_Ly_Hoc_Sinh_Backend.security;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtResponse {
    private final String jwtToken;
    private final String username;
    private final String role;
}
