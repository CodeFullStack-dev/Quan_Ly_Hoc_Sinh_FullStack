package com.example.Quan_Ly_Hoc_Sinh_Backend.dto.ScoreEntryDTOs;

import com.example.Quan_Ly_Hoc_Sinh_Backend.model.Enum.EScoreType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScoreEntryResponse {
    private Long id;
    private String studentName;
    private String subjectName;
    private String teacherName;
    private EScoreType scoreType;
    private BigDecimal scoreValue;
    private Date testDate;
}
