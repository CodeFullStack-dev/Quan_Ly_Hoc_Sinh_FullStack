package com.example.Quan_Ly_Hoc_Sinh_Backend.dto.StudentDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentRequest {

    // Các trường dữ liệu cơ bản
    private String codeStudent;
    private String fullNameStudent;
    private LocalDate dobStudent;
    private String genderStudent;
    private String phoneStudent;
    private String addressStudent;

    // ID ngoại lai (Foreign Keys)
    private Integer schoolId;
    private Integer classroomId;
}
