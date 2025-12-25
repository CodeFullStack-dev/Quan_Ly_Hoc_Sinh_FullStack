package com.example.Quan_Ly_Hoc_Sinh_Backend.dto.GradeBookDTOs;

import com.example.Quan_Ly_Hoc_Sinh_Backend.model.Enum.ESemester;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GradeBookResponse {
    private Long id;
    private String studentName;
    private String subjectName;
    private ESemester semester;
    private String schoolYear;
    private BigDecimal averageScore;
    private String rating; // Giỏi, Khá, Trung bình...
}
