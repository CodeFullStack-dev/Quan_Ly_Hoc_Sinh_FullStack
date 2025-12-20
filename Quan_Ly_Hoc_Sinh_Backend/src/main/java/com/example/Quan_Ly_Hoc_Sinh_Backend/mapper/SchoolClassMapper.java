package com.example.Quan_Ly_Hoc_Sinh_Backend.mapper;

import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.SchoolClassDTOs.SchoolClassRequest;
import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.SchoolClassDTOs.SchoolClassResponse;
import com.example.Quan_Ly_Hoc_Sinh_Backend.model.Entity.SchoolClass;
import com.example.Quan_Ly_Hoc_Sinh_Backend.model.Enum.EGradeLevel;
import org.springframework.stereotype.Component;

@Component
public class SchoolClassMapper {
    // Ánh xạ từ Request sang Entity
    public SchoolClass toEntity(SchoolClassRequest request) {
        if (request == null) return null;

        SchoolClass schoolClass = new SchoolClass();
        schoolClass.setClassName(request.getClassName());
        schoolClass.setGradeLevel(EGradeLevel.valueOf(request.getGradeLevel()));
        schoolClass.setSchoolYear(request.getSchoolYear());
        return schoolClass;
    }

    // Ánh xạ từ Entity sang Response
    public SchoolClassResponse toResponse(SchoolClass schoolClass) {
        if (schoolClass == null) return null;

        SchoolClassResponse response = new SchoolClassResponse();
        response.setId(schoolClass.getId());
        response.setClassName(schoolClass.getClassName());
        response.setGradeLevel(schoolClass.getGradeLevel().name());
        response.setSchoolYear(schoolClass.getSchoolYear());

        // Trích xuất tên trường
        if (schoolClass.getSchool() != null) {
            response.setSchoolName(schoolClass.getSchool().getSchoolName());
        }

        // Trích xuất tên giáo viên
        if (schoolClass.getHomeroomTeacher() != null) {
            response.setTeacherName(schoolClass.getHomeroomTeacher().getFullName());
        } else {
            response.setTeacherName("Chưa có");
        }

        return response;
    }
}
