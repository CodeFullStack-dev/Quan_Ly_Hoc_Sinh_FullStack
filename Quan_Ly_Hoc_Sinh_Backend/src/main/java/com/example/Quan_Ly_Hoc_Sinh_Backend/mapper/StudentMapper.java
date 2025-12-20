package com.example.Quan_Ly_Hoc_Sinh_Backend.mapper;

import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.StudentDTOs.StudentRequest;
import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.StudentDTOs.StudentResponse;
import com.example.Quan_Ly_Hoc_Sinh_Backend.model.Entity.Student;
import com.example.Quan_Ly_Hoc_Sinh_Backend.model.Enum.EGender;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {
    // Chuyển từ Request DTO sang Entity (Dùng cho Create/Update)
    public Student toEntity(StudentRequest request) {
        if (request == null) return null;

        Student student = new Student();
        student.setStudentCode(request.getStudentCode());
        student.setFullName(request.getFullName());
        student.setDateOfBirth(request.getDateOfBirth());
        student.setGender(EGender.valueOf(request.getGender()));
        student.setCurrentAddress(request.getCurrentAddress());
        return student;
    }

    // Chuyển từ Entity sang Response DTO (Dùng để trả về cho Frontend)
    public StudentResponse toResponse(Student student) {
        if (student == null) return null;

        StudentResponse response = new StudentResponse();
        response.setId(student.getId());
        response.setStudentCode(student.getStudentCode());
        response.setFullName(student.getFullName());

        // Đảm bảo gán các trường này để Postman hiển thị được
        response.setDateOfBirth(student.getDateOfBirth());
        response.setCurrentAddress(student.getCurrentAddress());

        // Sửa lỗi lấy gender: Lấy từ Entity student, chuyển Enum sang String
        if (student.getGender() != null) {
            response.setGender(student.getGender().name());
        }

        // Gán dữ liệu từ quan hệ N-1
        if (student.getSchoolClass() != null) {
            response.setClassName(student.getSchoolClass().getClassName());

            // Gán ID lớp học vào đây
            response.setSchoolClassId(student.getSchoolClass().getId());

            if (student.getSchoolClass().getSchool() != null) {
                response.setSchoolName(student.getSchoolClass().getSchool().getSchoolName());
            }
        }
        return response;
    }
}
