package com.example.Quan_Ly_Hoc_Sinh_Backend.dto.AcademicRecordDTOs;

import com.example.Quan_Ly_Hoc_Sinh_Backend.model.Enum.EConduct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AcademicRecordRequest {
    private Long studentId;
    private String schoolYear;
    private EConduct conduct;
    private String homeroomTeacherReview;
    private String parentFeedback;
    private String status;
}
