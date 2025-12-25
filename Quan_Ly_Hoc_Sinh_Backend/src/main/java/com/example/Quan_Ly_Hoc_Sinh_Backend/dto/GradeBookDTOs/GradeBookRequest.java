package com.example.Quan_Ly_Hoc_Sinh_Backend.dto.GradeBookDTOs;

import com.example.Quan_Ly_Hoc_Sinh_Backend.model.Enum.ESemester;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GradeBookRequest {
    private Long studentId;
    private Long subjectId;
    private ESemester semester;
    private String schoolYear;
    private BigDecimal averageScore; // Có thể nhập tay
    private String rating;           // Có thể nhập tay xếp loại
}
