package com.example.Quan_Ly_Hoc_Sinh_Backend.service.StudentCard;

import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.StudentCardDTOs.StudentCardResponse;
import org.springframework.web.multipart.MultipartFile;

public interface StudentCardService {
    StudentCardResponse issueCard(String studentCode, MultipartFile file);
    StudentCardResponse getCardByStudentCode(String studentCode);
    StudentCardResponse updateCardStatus(String studentCode, String status);
    void deleteCard(String studentCode);
}
