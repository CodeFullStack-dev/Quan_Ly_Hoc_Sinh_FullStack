package com.example.Quan_Ly_Hoc_Sinh_Backend.dto.StaffDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StaffRequest {
    // Các trường dữ liệu cơ bản
    private String fullNameStaff;
    private LocalDate dobStaff;
    private String genderStaff;
    private String phoneStaff;
    private String emailStaff;
    private String addressStaff;

    // ID ngoại lai (Foreign Keys)
    private Integer schoolId; // ID của School
    private Integer userId;   // ID của User (cho trường hợp Staff đã có tài khoản)
}
