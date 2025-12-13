package com.example.Quan_Ly_Hoc_Sinh_Backend.dto.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private String usernameUser;
    private String passwordUser; // Sẽ được mã hóa trong Service
    private boolean enabledUser = true;

    // ID của các Role được gán cho User này
    private List<Integer> roleIds;
}
