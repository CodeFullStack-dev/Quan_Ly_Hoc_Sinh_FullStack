package com.example.Quan_Ly_Hoc_Sinh_Backend.dto.StaffDTOs;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class StaffResponse {
    private int idStaff;
    private String fullNameStaff;
    private LocalDate dobStaff;
    private String genderStaff;
    private String phoneStaff;
    private String emailStaff;
    private String addressStaff;

    // Tên được ánh xạ từ các mối quan hệ Many-to-One / One-to-One
    private String schoolName;
    private String username;

    // Thông tin về Role (từ Entity User liên quan)
    private List<String> currentRoles;

    // Tên Lớp chủ nhiệm (vì listHomeClasses là List<Classroom>, ta chỉ lấy tên lớp đầu tiên)
    private String classroomName;

    // Thông tin tóm tắt về mối quan hệ One-to-Many
    private int totalAssignments;
    private int totalLessonLogs;
    private int totalScoreRecords;
}
