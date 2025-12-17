package com.example.Quan_Ly_Hoc_Sinh_Backend.dto.EmployeeDTOs;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponse {
    private Long id;
    private String username;
    private String fullName; // Thêm trường này để Mapper không bị lỗi setFullName
    private String email;
    private Set<String> roles;
    // Bỏ trường password đi nhé!
}
