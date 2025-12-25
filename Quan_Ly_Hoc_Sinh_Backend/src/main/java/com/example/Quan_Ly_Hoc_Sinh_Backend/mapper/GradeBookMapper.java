package com.example.Quan_Ly_Hoc_Sinh_Backend.mapper;

import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.GradeBookDTOs.GradeBookRequest;
import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.GradeBookDTOs.GradeBookResponse;
import com.example.Quan_Ly_Hoc_Sinh_Backend.model.Entity.GradeBook;
import org.springframework.stereotype.Component;

@Component
public class GradeBookMapper {

    /**
     * Chuyển từ Entity sang Response để trả về cho Web/Frontend hiển thị
     */
    public GradeBookResponse toResponse(GradeBook entity) {
        if (entity == null) return null;

        GradeBookResponse response = new GradeBookResponse();
        response.setId(entity.getId());
        response.setAverageScore(entity.getAverageScore());
        response.setRating(entity.getRating());
        response.setSemester(entity.getSemester());
        response.setSchoolYear(entity.getSchoolYear());

        // Lấy tên thay vì chỉ lấy ID để Frontend hiển thị dễ dàng
        if (entity.getSubject() != null) {
            response.setSubjectName(entity.getSubject().getSubjectName());
        }
        if (entity.getStudent() != null) {
            response.setStudentName(entity.getStudent().getFullName());
        }

        return response;
    }

    /**
     * Chuyển từ Request sang Entity (Dùng cho trường hợp giáo vụ nhập tay điểm trung bình)
     */
    public GradeBook toEntity(GradeBookRequest request) {
        if (request == null) return null;

        GradeBook entity = new GradeBook();
        entity.setAverageScore(request.getAverageScore());
        entity.setRating(request.getRating());
        entity.setSemester(request.getSemester());
        entity.setSchoolYear(request.getSchoolYear());

        // Lưu ý: Đối tượng Student và Subject thực sự sẽ được set ở Service
        // bằng cách tìm kiếm từ Repository thông qua StudentId và SubjectId

        return entity;
    }
}