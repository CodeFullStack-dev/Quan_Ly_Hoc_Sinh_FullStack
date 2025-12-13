package com.example.Quan_Ly_Hoc_Sinh_Backend.dto.ReportCardDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportCardResponse {
    private int idReportCard;
    private Double gpaReportCard;
    private String commentReportCard;

    // Thông tin chi tiết từ Student
    private int studentId;
    private String studentFullName;

    // Thông tin chi tiết từ Semester
    private int semesterId;
    private String semesterName;

    // Thông tin chi tiết từ Conduct (Hạnh kiểm)
    private int conductId;
    private String conductRating; // Ví dụ: Tốt, Khá, Trung bình
}
