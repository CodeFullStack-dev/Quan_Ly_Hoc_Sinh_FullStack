package com.example.Quan_Ly_Hoc_Sinh_Backend.dto.LessonLogDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LessonLogRequest {
    private LocalDate lessonDateLessonLog;
    private String contentLessonLog; // Nội dung bài học/chủ đề

    // Khóa ngoại (Sử dụng ID)
    private Integer classroomId; // Lớp học
    private Integer subjectId;   // Môn học
    private Integer teacherId;   // Giáo viên dạy
    private Integer semesterId;  // Học kỳ
}
