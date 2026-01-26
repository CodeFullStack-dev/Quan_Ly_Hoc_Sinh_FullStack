package com.example.Quan_Ly_Hoc_Sinh_Backend.dto.StudentCardDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentCardResponse {
    private Long id;
    private String studentCode;
    private String studentFullName;
    private String cardNumber;
    private String photoUrl;
    private String status;
    private Date issueDate;
    private Date expiryDate;
}
