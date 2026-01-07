package com.example.Quan_Ly_Hoc_Sinh_Backend.mapper;

import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.LessonLogDTOs.LessonLogRequest;
import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.LessonLogDTOs.LessonLogResponse;
import com.example.Quan_Ly_Hoc_Sinh_Backend.model.Entity.LessonLog;
import org.springframework.stereotype.Component;

@Component
public class LessonLogMapper {

    public LessonLog toEntity(LessonLogRequest request) {
        if(request == null) return null;
        LessonLog entity = new LessonLog();
        entity.setLessonDate(request.getLessonDate());
        entity.setPeriodNumber(request.getPeriodNumber());
        entity.setLessonContent(request.getLessonContent());
        entity.setNotes(request.getNotes()); // Khớp với Entity "notes"
        return entity;
    }

    public LessonLogResponse toResponse(LessonLog entity) {
        if(entity == null) return null;
        LessonLogResponse response = new LessonLogResponse();
        response.setId(entity.getId());
        response.setLessonDate(entity.getLessonDate());
        response.setPeriodNumber(entity.getPeriodNumber());
        response.setLessonContent(entity.getLessonContent());
        response.setNotes(entity.getNotes());

        // BỔ SUNG: Ánh xạ thông tin từ các quan hệ để FE hiển thị
        if (entity.getSchoolClass() != null) {
            response.setClassName(entity.getSchoolClass().getClassName());
        }
        if (entity.getTeacher() != null) {
            response.setTeacherName(entity.getTeacher().getFullName());
        }
        if (entity.getSubject() != null) {
            response.setSubjectName(entity.getSubject().getSubjectName());
        }

        return response;
    }
}
