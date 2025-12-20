package com.example.Quan_Ly_Hoc_Sinh_Backend.dto.SchoolClassDTOs;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchoolClassRequest {
    private String className;
    private String gradeLevel; // Truyền chuỗi như "GRADE_10"
    private String schoolYear; // Ví dụ "2024-2025"
    private Long schoolId;
    private Long teacherId;
}
