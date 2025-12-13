package com.example.Quan_Ly_Hoc_Sinh_Backend.dto.StudentCardDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentCardResponse {
    private int idStudentCard;
    private String avatarUrlStudentCard;
    private LocalDate issueDateStudentCard;
    private LocalDate expiredDateStudentCard;

    // Thông tin chi tiết từ Student (Nếu cần, nhưng thường chỉ cần id và tên)
    private int studentId;
    private String studentFullName;
}
