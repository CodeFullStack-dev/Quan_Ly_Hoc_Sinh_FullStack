package com.example.Quan_Ly_Hoc_Sinh_Backend.mapper;

import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.SubjectDTOs.SubjectRequest;
import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.SubjectDTOs.SubjectResponse;
import com.example.Quan_Ly_Hoc_Sinh_Backend.entity.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SubjectMapper {

    // --- toEntity (từ Request) ---
    @Mapping(target = "idSubject", ignore = true)
    // Bỏ qua các mối quan hệ One-to-Many
    @Mapping(target = "listScoreRecords", ignore = true)
    @Mapping(target = "listLessonLogs", ignore = true)
    @Mapping(target = "listTeacherAssignments", ignore = true)
    Subject toEntity(SubjectRequest request);

    // --- toResponse (từ Entity) ---
    // Tính toán số lượng cho các trường tóm tắt (sử dụng tên chính xác từ Entity)
    @Mapping(target = "totalScoreRecords",
            expression = "java(entity.getListScoreRecords() != null ? entity.getListScoreRecords().size() : 0)")
    @Mapping(target = "totalLessonLogs",
            expression = "java(entity.getListLessonLogs() != null ? entity.getListLessonLogs().size() : 0)")
    @Mapping(target = "totalTeacherAssignments",
            expression = "java(entity.getListTeacherAssignments() != null ? entity.getListTeacherAssignments().size() : 0)")
    SubjectResponse toResponse(Subject entity);

    List<SubjectResponse> toResponseList(List<Subject> entities);
}
