package com.example.Quan_Ly_Hoc_Sinh_Backend.dto.SchoolDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchoolResponse {
    private Long id;
    private String schoolName;
    private String address;
    private String phoneNumber;
    private String email;
}
