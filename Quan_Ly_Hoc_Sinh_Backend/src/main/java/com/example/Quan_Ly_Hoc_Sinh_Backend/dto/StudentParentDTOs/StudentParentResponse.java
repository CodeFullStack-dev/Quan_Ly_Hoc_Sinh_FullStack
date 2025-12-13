package com.example.Quan_Ly_Hoc_Sinh_Backend.dto.StudentParentDTOs;

import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.ParentDTOs.ParentResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentParentResponse {

    private int idStudentParent;
    private String relationStudentParent;

    // Thông tin chi tiết từ Student
    private int studentId;
    private String studentFullName;

    // Thông tin chi tiết từ Parent
    private int parentId;
    private String parentFullName;
    private String parentPhone;
}
