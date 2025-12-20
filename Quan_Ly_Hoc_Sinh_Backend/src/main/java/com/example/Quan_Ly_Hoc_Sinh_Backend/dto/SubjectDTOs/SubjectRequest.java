package com.example.Quan_Ly_Hoc_Sinh_Backend.dto.SubjectDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectRequest {
    private String subjectCode;
    private String subjectName;
}
