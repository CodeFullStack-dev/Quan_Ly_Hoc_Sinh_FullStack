package com.example.Quan_Ly_Hoc_Sinh_Backend.dto.UserRoleDTOs;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleRequest {
    // Khóa ngoại (Sử dụng ID)
    private Integer userId;
    private Integer roleId;
}
