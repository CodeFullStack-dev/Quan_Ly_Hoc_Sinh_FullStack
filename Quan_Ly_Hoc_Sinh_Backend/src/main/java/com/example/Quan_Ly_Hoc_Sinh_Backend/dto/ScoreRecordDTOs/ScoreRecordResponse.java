package com.example.Quan_Ly_Hoc_Sinh_Backend.dto.ScoreRecordDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScoreRecordResponse {
    private int idScoreRecord;
    private Double scoreRecordValue;

    // Thông tin chi tiết từ Student
    private int studentId;
    private String studentFullName;

    // Thông tin chi tiết từ Teacher (Staff)
    private int teacherId;
    private String teacherFullName;

    // Thông tin chi tiết từ Semester
    private int semesterId;
    private String semesterName;

    // Thông tin chi tiết từ ScoreType
    private int scoreTypeId;
    private String scoreTypeName;

    // Thông tin chi tiết từ Subject
    private int subjectId;
    private String subjectName;
}
