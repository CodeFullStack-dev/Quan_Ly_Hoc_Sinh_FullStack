package com.example.Quan_Ly_Hoc_Sinh_Backend.dto.AttendanceDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceRequest {
    // Thông tin điểm danh
    private LocalDate dateAttendance;
    private String statusAttendance; // Ví dụ: "PRESENT", "ABSENT", "LATE"

    // Khóa ngoại (Sử dụng ID)
    private Integer studentId;   // Học sinh
    private Integer classroomId; // Lớp học (Đảm bảo tính nhất quán)
    private Integer semesterId;  // Học kỳ
}
