package com.example.Quan_Ly_Hoc_Sinh_Backend.mapper;

import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.AcademicRecordDTOs.AcademicRecordRequest;
import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.AcademicRecordDTOs.AcademicRecordResponse;
import com.example.Quan_Ly_Hoc_Sinh_Backend.model.Entity.AcademicRecord;
import org.springframework.stereotype.Component;

@Component
public class AcademicRecordMapper {
    public AcademicRecord toEntity(AcademicRecordRequest request) {
        AcademicRecord entity = new AcademicRecord();
        entity.setSchoolYear(request.getSchoolYear());
        entity.setConduct(request.getConduct());
        entity.setHomeroomTeacherReview(request.getHomeroomTeacherReview());
        entity.setParentFeedback(request.getParentFeedback());
        entity.setStatus(request.getStatus());
        return entity;
    }

    public AcademicRecordResponse toResponse(AcademicRecord entity) {
        AcademicRecordResponse response = new AcademicRecordResponse();
        response.setId(entity.getId());
        response.setStudentId(entity.getStudent().getId());
        response.setStudentFullName(entity.getStudent().getFullName());
        response.setSchoolYear(entity.getSchoolYear());
        response.setConduct(entity.getConduct());
        response.setYearAverageScore(entity.getYearAverageScore());
        response.setAcademicPerformanceRating(entity.getAcademicPerformanceRating());
        response.setHomeroomTeacherReview(entity.getHomeroomTeacherReview());
        response.setParentFeedback(entity.getParentFeedback());
        response.setStatus(entity.getStatus());
        return response;
    }
}
