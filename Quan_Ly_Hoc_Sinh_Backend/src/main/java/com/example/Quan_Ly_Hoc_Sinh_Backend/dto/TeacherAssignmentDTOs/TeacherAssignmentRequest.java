package com.example.Quan_Ly_Hoc_Sinh_Backend.dto.TeacherAssignmentDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherAssignmentRequest {
    // Khóa ngoại (Sử dụng ID)
    private Integer teacherId;   // Staff
    private Integer subjectId;   // Subject
    private Integer classroomId; // Classroom
    private Integer semesterId;  // Semester
}
