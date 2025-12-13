package com.example.Quan_Ly_Hoc_Sinh_Backend.dto.StudentCardDTOs;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentCardRequest {
    // Các trường dữ liệu cơ bản
    private String avatarUrlStudentCard;
    private LocalDate issueDateStudentCard;
    private LocalDate expiredDateStudentCard;

    // ID ngoại lai (Foreign Key)
    private Integer studentId; // ID của Học sinh mà thẻ này thuộc về
}
