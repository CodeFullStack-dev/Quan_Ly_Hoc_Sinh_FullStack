package com.example.Quan_Ly_Hoc_Sinh_Backend.mapper;

import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.SubjectDTOs.SubjectRequest;
import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.SubjectDTOs.SubjectResponse;
import com.example.Quan_Ly_Hoc_Sinh_Backend.model.Entity.Subject;
import org.springframework.stereotype.Component;

@Component
public class SubjectMapper {
    // Chuyển từ Request DTO sang Entity để lưu vào DB
    public Subject toEntity(SubjectRequest request) {
        if (request == null) return null;

        Subject subject = new Subject();
        subject.setSubjectCode(request.getSubjectCode());
        subject.setSubjectName(request.getSubjectName());
        return subject;
    }

    // Chuyển từ Entity sang Response DTO để trả về cho Frontend
    public SubjectResponse toResponse(Subject subject) {
        if (subject == null) return null;

        SubjectResponse response = new SubjectResponse();
        response.setId(subject.getId());
        response.setSubjectCode(subject.getSubjectCode());
        response.setSubjectName(subject.getSubjectName());
        return response;
    }
}
