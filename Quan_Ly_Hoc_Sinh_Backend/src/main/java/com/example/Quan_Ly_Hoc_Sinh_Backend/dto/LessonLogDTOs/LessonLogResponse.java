package com.example.Quan_Ly_Hoc_Sinh_Backend.dto.LessonLogDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LessonLogResponse {
    private int idLessonLog;
    private LocalDate lessonDateLessonLog;
    private String contentLessonLog;

    // Thông tin chi tiết từ Classroom
    private int classroomId;
    private String classroomName;

    // Thông tin chi tiết từ Subject
    private int subjectId;
    private String subjectName;

    // Thông tin chi tiết từ Teacher (Staff)
    private int teacherId;
    private String teacherFullName;

    // Thông tin chi tiết từ Semester
    private int semesterId;
    private String semesterName;
}
