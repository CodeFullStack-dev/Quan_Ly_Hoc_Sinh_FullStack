package com.example.Quan_Ly_Hoc_Sinh_Backend.dto.SubjectDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectResponse {
    private int idSubject;
    private String nameSubject;
    private String descriptionSubject;

    // Thông tin tóm tắt về mối quan hệ (tính toán trong Mapper)
    private int totalScoreRecords;
    private int totalLessonLogs;
    private int totalTeacherAssignments;
}
