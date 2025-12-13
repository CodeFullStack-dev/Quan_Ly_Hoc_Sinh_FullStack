package com.example.Quan_Ly_Hoc_Sinh_Backend.mapper;

import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.StudentParentDTOs.StudentParentRequest;
import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.StudentParentDTOs.StudentParentResponse;
import com.example.Quan_Ly_Hoc_Sinh_Backend.entity.StudentParent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

// Import ParentMapper để tái sử dụng việc ánh xạ Parent Entity sang ParentResponse
// Đặt unmappedTargetPolicy = IGNORE để xử lý lỗi "Unknown property 'student'"
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StudentParentMapper {

    // --- toEntity (từ Request) ---
    // Các trường Entity đối tượng (Student, Parent) phải được ignore
    @Mapping(target = "idStudentParent", ignore = true)
    @Mapping(target = "student", ignore = true)
    @Mapping(target = "parent", ignore = true)
    StudentParent toEntity(StudentParentRequest request);

    // --- toResponse (từ Entity) ---

    // 1. Ánh xạ ID và Tên từ đối tượng Student
    @Mapping(source = "student.idStudent", target = "studentId")
    @Mapping(source = "student.fullNameStudent", target = "studentFullName") // Giả định Student có trường fullNameStudent

    // 2. Ánh xạ ID, Tên và Phone từ đối tượng Parent
    @Mapping(source = "parent.idParent", target = "parentId")
    @Mapping(source = "parent.fullNameParent", target = "parentFullName")
    @Mapping(source = "parent.phoneParent", target = "parentPhone")

    StudentParentResponse toResponse(StudentParent entity);

    List<StudentParentResponse> toResponseList(List<StudentParent> entities);
}