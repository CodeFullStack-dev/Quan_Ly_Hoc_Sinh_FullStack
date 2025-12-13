package com.example.Quan_Ly_Hoc_Sinh_Backend.dto.SemesterDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SemesterResponse {
    private int idSemester;
    private String nameSemester;
    private String academicYearSemester;

    // Thông tin tóm tắt về mối quan hệ (tính toán trong Mapper)
    private int totalScoreRecords;
    private int totalReportCards;
    private int totalLessonLogs;
    private int totalAttendances;
    private int totalTeacherAssignments;
}
