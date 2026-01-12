package com.example.Quan_Ly_Hoc_Sinh_Backend.dto.ParentDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParentRequest {
    private String fullName;
    private String phoneNumber;
    private String email;
    private String job;
    private String address;
    private String relationship;
    private String studentId;
}
