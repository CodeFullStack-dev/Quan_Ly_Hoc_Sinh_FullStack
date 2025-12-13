package com.example.Quan_Ly_Hoc_Sinh_Backend.dto.AttendanceDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceResponse {
    private int idAttendance;
    private LocalDate dateAttendance;
    private String statusAttendance;

    // Thông tin chi tiết từ Student
    private int studentId;
    private String studentFullName;

    // Thông tin chi tiết từ Classroom
    private int classroomId;
    private String classroomName;

    // Thông tin chi tiết từ Semester
    private int semesterId;
    private String semesterName;
}
