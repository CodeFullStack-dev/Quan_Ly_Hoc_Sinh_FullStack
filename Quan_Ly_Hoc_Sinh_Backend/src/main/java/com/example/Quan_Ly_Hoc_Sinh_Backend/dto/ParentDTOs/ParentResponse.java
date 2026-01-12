package com.example.Quan_Ly_Hoc_Sinh_Backend.dto.ParentDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParentResponse {
    private Long id;
    private String fullName;
    private String phoneNumber;
    private String email;
    private String relationship;
    private String studentName; //trả về tên học sinh
}
