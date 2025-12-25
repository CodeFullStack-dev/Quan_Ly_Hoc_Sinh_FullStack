package com.example.Quan_Ly_Hoc_Sinh_Backend.dto.ScoreEntryDTOs;

import com.example.Quan_Ly_Hoc_Sinh_Backend.model.Enum.EScoreType;
import com.example.Quan_Ly_Hoc_Sinh_Backend.model.Enum.ESemester;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScoreEntryRequest {
    private Long studentId;
    private Long subjectId;
    private Long teacherId;
    private EScoreType scoreType;  // Enum: ORAL_TEST, FIFTEEN_MIN_TEST...
    private BigDecimal scoreValue;
    private Date testDate;

    // Hai trường này cần thiết để Service biết cần tính điểm trung bình cho kỳ nào, năm nào
    private ESemester semester;    // Enum: SEMESTER_I, SEMESTER_II
    private String schoolYear;     // Ví dụ: "2023-2024"
}
