package com.example.Quan_Ly_Hoc_Sinh_Backend.dto.SchoolClassDTOs;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchoolClassResponse {
    private Long id;
    private String className;
    private String gradeLevel;
    private String schoolYear;
    private String schoolName;
    private String teacherName;
    private String schoolClassId;
}
