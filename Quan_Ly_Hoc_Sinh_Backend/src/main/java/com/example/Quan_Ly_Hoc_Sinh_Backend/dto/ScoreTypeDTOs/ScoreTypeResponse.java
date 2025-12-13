package com.example.Quan_Ly_Hoc_Sinh_Backend.dto.ScoreTypeDTOs;

import lombok.Data;

@Data
public class ScoreTypeResponse {
    private Integer idScoreType;

    private String nameScoreType;
    private double weightScoreType;
    // Thống kê số lượng bản ghi điểm đã được nhập theo loại điểm này
    private int totalScoreRecords;
}
