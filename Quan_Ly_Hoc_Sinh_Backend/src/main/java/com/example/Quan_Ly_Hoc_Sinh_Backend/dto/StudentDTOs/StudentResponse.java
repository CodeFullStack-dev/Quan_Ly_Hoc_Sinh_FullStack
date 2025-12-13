package com.example.Quan_Ly_Hoc_Sinh_Backend.dto.StudentDTOs;

import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.StudentCardDTOs.StudentCardResponse;
import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.StudentParentDTOs.StudentParentResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponse {
    private int idStudent;
    private String codeStudent;
    private String fullNameStudent;
    private LocalDate dobStudent;
    private String genderStudent;
    private String phoneStudent;
    private String addressStudent;

    // Tên được ánh xạ từ các mối quan hệ Many-to-One
    private String schoolName;
    private String classroomName;
    private String gradeLevel;

    // Chi tiết Thẻ học sinh (One-to-One)
    private StudentCardResponse studentCard;

    // Danh sách mối quan hệ với Phụ huynh (One-to-Many qua Entity trung gian)
    private List<StudentParentResponse> parents;

    // Thông tin tóm tắt về mối quan hệ One-to-Many
    private int totalScoreRecords;
    private int totalReportCards;
    private int totalAttendances;
}
