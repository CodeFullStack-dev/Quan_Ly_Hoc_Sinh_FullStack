package com.example.Quan_Ly_Hoc_Sinh_Backend.mapper;


import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.SchoolDTOs.SchoolRequest;
import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.SchoolDTOs.SchoolResponse;
import com.example.Quan_Ly_Hoc_Sinh_Backend.model.Entity.School;
import org.springframework.stereotype.Component;

@Component
public class SchoolMapper {

    // Chuyển từ Entity sang Response (để trả về API)
    public SchoolResponse toResponse(School school) {
        if (school == null) return null;

        SchoolResponse response = new SchoolResponse();
        response.setId(school.getId());
        response.setSchoolName(school.getSchoolName());
        response.setAddress(school.getAddress());
        response.setPhoneNumber(school.getPhoneNumber());
        response.setEmail(school.getEmail());

        return response;
    }

    // Chuyển từ Request sang Entity (để chuẩn bị lưu vào DB)
    public School toEntity(SchoolRequest request) {
        if (request == null) return null;

        School school = new School();
        school.setSchoolName(request.getSchoolName());
        school.setAddress(request.getAddress());
        school.setPhoneNumber(request.getPhoneNumber());
        school.setEmail(request.getEmail());

        return school;
    }
}
