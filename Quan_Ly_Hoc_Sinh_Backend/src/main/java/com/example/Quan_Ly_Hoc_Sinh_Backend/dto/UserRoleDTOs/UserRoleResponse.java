package com.example.Quan_Ly_Hoc_Sinh_Backend.dto.UserRoleDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleResponse {
    private int idUserRole;

    // Thông tin chi tiết từ các mối quan hệ Many-to-One
    private int userId;
    private String username;
    private int roleId;
    private String roleName;
}
