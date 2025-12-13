package com.example.Quan_Ly_Hoc_Sinh_Backend.dto.ReportCardDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportCardRequest {
    // Các trường dữ liệu cơ bản
    private Double gpaReportCard;
    private String commentReportCard;

    // ID ngoại lai (Foreign Keys)
    private Integer studentId;
    private Integer semesterId;
    private Integer conductId; // ID của Conduct (Hạnh kiểm)
}
