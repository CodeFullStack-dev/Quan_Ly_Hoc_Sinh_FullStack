package com.example.Quan_Ly_Hoc_Sinh_Backend.dto.ParentDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParentRequest {
    // Các trường dữ liệu cơ bản
    private String fullNameParent;
    private String phoneParent;
    private String emailParent;
    private String addressParent;

    // ID ngoại lai (Foreign Key)
    private Integer userId; // ID của User (cho trường hợp Phụ huynh đã có tài khoản)
}
