package com.example.Quan_Ly_Hoc_Sinh_Backend.dto.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private int idUser;
    private String usernameUser;
    private Boolean enabledUser;

    // Danh sách Role (Chỉ lấy Tên Role)
    private List<String> currentRoles;
}
