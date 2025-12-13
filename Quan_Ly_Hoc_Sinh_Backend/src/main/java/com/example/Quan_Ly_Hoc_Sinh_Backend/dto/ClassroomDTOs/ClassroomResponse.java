package com.example.Quan_Ly_Hoc_Sinh_Backend.dto.ClassroomDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassroomResponse {
    private int idClassroom;
    private String nameClassroom;
    private String gradeLevelClassroom;
    private String academicYearClassroom;

    // Tên được ánh xạ từ các mối quan hệ Many-to-One
    private String schoolName;
    private String homeroomTeacherName;

    // Thông tin tóm tắt về mối quan hệ (tính toán trong Mapper)
    private int totalStudents;
    private int totalLessonLogs;
    private int totalAttendances;
    private int totalTeacherAssignments;
}
