package com.example.Quan_Ly_Hoc_Sinh_Backend.dto.StudentCardDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentCardRequest {
    private String studentCode;
    private String status;
}
