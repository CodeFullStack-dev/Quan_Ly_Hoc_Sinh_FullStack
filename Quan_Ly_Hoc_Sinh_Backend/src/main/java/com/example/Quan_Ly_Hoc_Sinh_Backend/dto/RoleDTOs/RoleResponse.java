package com.example.Quan_Ly_Hoc_Sinh_Backend.dto.RoleDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleResponse {
    private int idRole;
    private String nameRole;

    // Thống kê số lượng người dùng có Role này
    private int totalUsers;
}
