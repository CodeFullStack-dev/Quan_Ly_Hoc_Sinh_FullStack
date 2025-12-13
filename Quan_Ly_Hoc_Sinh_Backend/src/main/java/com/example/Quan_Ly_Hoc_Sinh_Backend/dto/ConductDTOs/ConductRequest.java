package com.example.Quan_Ly_Hoc_Sinh_Backend.dto.ConductDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConductRequest {
    private String codeConduct;
    private String nameConduct;
}
