package com.example.Quan_Ly_Hoc_Sinh_Backend.dto.StudentDTOs;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentRequest {
    private String studentCode;
    private String fullName;
    private Date dateOfBirth;
    private String gender;
    private String currentAddress;
    private Long schoolClassId;
}
