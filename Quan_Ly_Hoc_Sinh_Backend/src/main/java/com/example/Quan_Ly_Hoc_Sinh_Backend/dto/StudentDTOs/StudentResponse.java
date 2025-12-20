package com.example.Quan_Ly_Hoc_Sinh_Backend.dto.StudentDTOs;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponse {
    private Long id;
    private String studentCode;
    private String fullName;
    private String className;
    private String schoolName;
    private Date dateOfBirth;
    private String gender;
    private String currentAddress;
    // Thêm trường này để trả về ID của lớp học
    private Long schoolClassId;
}
