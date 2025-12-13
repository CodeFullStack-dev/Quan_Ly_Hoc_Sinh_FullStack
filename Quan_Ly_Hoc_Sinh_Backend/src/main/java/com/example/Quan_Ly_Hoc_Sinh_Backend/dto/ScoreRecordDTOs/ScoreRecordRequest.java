package com.example.Quan_Ly_Hoc_Sinh_Backend.dto.ScoreRecordDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScoreRecordRequest {
    // Các trường dữ liệu
    private Double scoreRecordValue; // Giá trị điểm

    // Khóa ngoại (Sử dụng ID)
    private Integer studentId;   // Học sinh
    private Integer teacherId;   // Giáo viên nhập điểm
    private Integer semesterId;  // Học kỳ
    private Integer scoreTypeId; // Loại điểm (Miệng, 1 Tiết, Cuối kỳ...)
    private Integer subjectId;   // Môn học
}
