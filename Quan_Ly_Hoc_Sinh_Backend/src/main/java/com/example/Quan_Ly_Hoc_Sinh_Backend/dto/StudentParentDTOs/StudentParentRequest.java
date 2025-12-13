package com.example.Quan_Ly_Hoc_Sinh_Backend.dto.StudentParentDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentParentRequest {

    // ID của hai đối tượng liên quan
    private Integer studentId;
    private Integer parentId;

    // Thông tin thêm
    private String relationStudentParent;
}
