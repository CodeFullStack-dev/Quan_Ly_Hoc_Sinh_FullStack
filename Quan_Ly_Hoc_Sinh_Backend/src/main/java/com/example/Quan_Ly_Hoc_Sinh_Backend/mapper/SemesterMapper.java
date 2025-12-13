package com.example.Quan_Ly_Hoc_Sinh_Backend.mapper;

import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.SemesterDTOs.SemesterRequest;
import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.SemesterDTOs.SemesterResponse;
import com.example.Quan_Ly_Hoc_Sinh_Backend.entity.Semester;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SemesterMapper {

    // --- toEntity (từ Request) ---
    @Mapping(target = "idSemester", ignore = true)
    // Bỏ qua các mối quan hệ One-to-Many
    @Mapping(target = "listScoreRecords", ignore = true)
    @Mapping(target = "listReportCards", ignore = true)
    @Mapping(target = "listLessonLogs", ignore = true)
    @Mapping(target = "listAttendances", ignore = true)
    @Mapping(target = "listTeacherAssignments", ignore = true)
    Semester toEntity(SemesterRequest request);

    // --- toResponse (từ Entity) ---
    // Tính toán số lượng cho các trường tóm tắt (sử dụng tên chính xác từ Entity)
    @Mapping(target = "totalScoreRecords",
            expression = "java(entity.getListScoreRecords() != null ? entity.getListScoreRecords().size() : 0)")
    @Mapping(target = "totalReportCards",
            expression = "java(entity.getListReportCards() != null ? entity.getListReportCards().size() : 0)")
    @Mapping(target = "totalLessonLogs",
            expression = "java(entity.getListLessonLogs() != null ? entity.getListLessonLogs().size() : 0)")
    @Mapping(target = "totalAttendances",
            expression = "java(entity.getListAttendances() != null ? entity.getListAttendances().size() : 0)")
    @Mapping(target = "totalTeacherAssignments",
            expression = "java(entity.getListTeacherAssignments() != null ? entity.getListTeacherAssignments().size() : 0)")
    SemesterResponse toResponse(Semester entity);

    List<SemesterResponse> toResponseList(List<Semester> entities);
}
