package com.example.Quan_Ly_Hoc_Sinh_Backend.dto.SchoolDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchoolResponse {
    private Integer idSchool;
    private String nameSchool;
    private String addressSchool;
    private String phoneSchool;
    private String emailSchool;

    private int totalClassrooms;
    private int totalStaffs;
    private int totalStudents;
}
