package com.example.Quan_Ly_Hoc_Sinh_Backend.dto.AcademicRecordDTOs;

import com.example.Quan_Ly_Hoc_Sinh_Backend.model.Enum.EConduct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AcademicRecordResponse {
    private Long id;
    private Long studentId;
    private String studentFullName;
    private String schoolYear;
    private EConduct conduct;
    private BigDecimal yearAverageScore;
    private String academicPerformanceRating;
    private String homeroomTeacherReview;
    private String parentFeedback;
    private String status;
}
