package com.example.Quan_Ly_Hoc_Sinh_Backend.mapper;

import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.StudentCardDTOs.StudentCardResponse;
import com.example.Quan_Ly_Hoc_Sinh_Backend.model.Entity.StudentCard;
import org.springframework.stereotype.Component;

@Component
public class StudentCardMapper {
    public StudentCardResponse toStudentCardResponse(StudentCard studentCard) {
        if(studentCard == null) return null;

        StudentCardResponse response = new StudentCardResponse();
        response.setId(studentCard.getId());
        response.setCardNumber(studentCard.getCardNumber());
        response.setIssueDate(studentCard.getIssueDate());
        response.setExpiryDate(studentCard.getExpiryDate());
        response.setPhotoUrl(studentCard.getPhotoUrl());
        response.setStatus(studentCard.getStatus());

        //Lấy thông tin từ thực thể Student liên kết
        if(studentCard.getStatus() != null) {
            response.setStudentCode(studentCard.getStudent().getStudentCode());
            response.setStudentFullName(studentCard.getStudent().getFullName());
        }

        return response;
    }
}
