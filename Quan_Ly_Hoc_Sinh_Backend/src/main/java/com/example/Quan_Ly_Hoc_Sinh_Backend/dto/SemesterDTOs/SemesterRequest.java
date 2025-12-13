package com.example.Quan_Ly_Hoc_Sinh_Backend.dto.SemesterDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SemesterRequest {
    private String nameSemester;
    private String academicYearSemester;

}
