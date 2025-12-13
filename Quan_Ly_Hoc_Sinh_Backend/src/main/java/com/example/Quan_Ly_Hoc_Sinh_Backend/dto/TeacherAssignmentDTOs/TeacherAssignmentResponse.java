package com.example.Quan_Ly_Hoc_Sinh_Backend.dto.TeacherAssignmentDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherAssignmentResponse {
    private int idTeacherAssignment; // Lưu ý: Tên trường trong Entity là id_teacher_assignment

    // Thông tin chi tiết từ Staff (Giáo viên)
    private int teacherId;
    private String teacherFullName;

    // Thông tin chi tiết từ Subject (Môn học)
    private int subjectId;
    private String subjectName;

    // Thông tin chi tiết từ Classroom (Lớp học)
    private int classroomId;
    private String classroomName;
    private String classroomGradeLevel;

    // Thông tin chi tiết từ Semester (Học kỳ)
    private int semesterId;
    private String semesterName;
}
