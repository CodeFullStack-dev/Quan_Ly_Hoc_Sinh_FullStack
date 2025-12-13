package com.example.Quan_Ly_Hoc_Sinh_Backend.mapper;


import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.SchoolDTOs.SchoolRequest;
import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.SchoolDTOs.SchoolResponse;
import com.example.Quan_Ly_Hoc_Sinh_Backend.entity.School;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

// unmappedTargetPolicy = IGNORE để tránh lỗi biên dịch khi DTO không có tất cả các trường Entity.
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SchoolMapper {

    // --- toEntity (từ Request) ---
    @Mapping(target = "idSchool", ignore = true)
    // Bỏ qua các mối quan hệ One-to-Many
    @Mapping(target = "listClassrooms", ignore = true)
    @Mapping(target = "listStaffs", ignore = true)
    @Mapping(target = "listStudents", ignore = true)
    School toEntity(SchoolRequest request);

    // --- toResponse (từ Entity) ---
    // Tính toán số lượng cho các trường tóm tắt
    @Mapping(target = "totalClassrooms",
            expression = "java(entity.getListClassrooms() != null ? entity.getListClassrooms().size() : 0)")
    @Mapping(target = "totalStaffs",
            expression = "java(entity.getListStaffs() != null ? entity.getListStaffs().size() : 0)")
    @Mapping(target = "totalStudents",
            expression = "java(entity.getListStudents() != null ? entity.getListStudents().size() : 0)")
    SchoolResponse toResponse(School entity);

    List<SchoolResponse> toResponseList(List<School> entities);
}
