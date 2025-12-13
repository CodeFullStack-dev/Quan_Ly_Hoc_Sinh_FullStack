package com.example.Quan_Ly_Hoc_Sinh_Backend.dto.ParentDTOs;

import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.StudentParentDTOs.StudentParentResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParentResponse {
    private int idParent;
    private String fullNameParent;
    private String phoneParent;
    private String emailParent;
    private String addressParent;

    // Tên được ánh xạ từ User
    private String username;

    // Danh sách các mối quan hệ với Học sinh (sẽ được ánh xạ bằng StudentParentMapper)
    private List<StudentParentResponse> studentRelations;
}
