package com.example.Quan_Ly_Hoc_Sinh_Backend.mapper;

import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.ScoreEntryDTOs.ScoreEntryRequest;
import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.ScoreEntryDTOs.ScoreEntryResponse;
import com.example.Quan_Ly_Hoc_Sinh_Backend.model.Entity.ScoreEntry;
import org.springframework.stereotype.Component;

@Component
public class ScoreEntryMapper {
    public ScoreEntryResponse toResponse(ScoreEntry entity) {
        if (entity == null) return null;
        ScoreEntryResponse response = new ScoreEntryResponse();
        response.setId(entity.getId());
        response.setScoreValue(entity.getScoreValue());
        response.setScoreType(entity.getScoreType()); // Đảm bảo DTO cũng là kiểu EScoreType
        response.setTestDate(entity.getTestDate());

        if (entity.getStudent() != null) response.setStudentName(entity.getStudent().getFullName());
        if (entity.getSubject() != null) response.setSubjectName(entity.getSubject().getSubjectName());
        if (entity.getTeacher() != null) response.setTeacherName(entity.getTeacher().getFullName());

        return response;
    }

    public ScoreEntry toEntity(ScoreEntryRequest request) {
        if (request == null) return null;
        ScoreEntry entity = new ScoreEntry();
        entity.setScoreValue(request.getScoreValue());
        entity.setScoreType(request.getScoreType());
        entity.setTestDate(request.getTestDate());
        return entity;
    }
}