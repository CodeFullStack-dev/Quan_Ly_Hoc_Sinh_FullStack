package com.example.Quan_Ly_Hoc_Sinh_Backend.dto.SchoolDTOs;

import lombok.Data;

@Data
public class SchoolResponse {
    private Long id;
    private String schoolName;
    private String address;
    private String phoneNumber;
    private String email;
}
