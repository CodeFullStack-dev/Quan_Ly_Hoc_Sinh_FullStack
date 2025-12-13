package com.example.Quan_Ly_Hoc_Sinh_Backend.dto.ConductDTOs;

import lombok.Data;

@Data
public class ConductResponse {
    private Integer idConduct;

    private String codeConduct;
    private String nameConduct;

    // Thống kê số lượng học sinh đạt hạnh kiểm này trong các kỳ (nếu được fetch LAZY)
    private int totalReportCards;
}
