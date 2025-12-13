package com.example.Quan_Ly_Hoc_Sinh_Backend.mapper;

import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.ClassroomDTOs.ClassroomRequest;
import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.ClassroomDTOs.ClassroomResponse;
import com.example.Quan_Ly_Hoc_Sinh_Backend.entity.Classroom;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClassroomMapper {

    // --- toEntity (từ Request) ---
    // Các trường Entity đối tượng (School, Staff) phải được ignore
    @Mapping(target = "idClassroom", ignore = true)
    @Mapping(target = "school", ignore = true)
    @Mapping(target = "homeroomTeacher", ignore = true)

    // Bỏ qua các mối quan hệ One-to-Many
    @Mapping(target = "listStudents", ignore = true)
    @Mapping(target = "listLessonLogs", ignore = true)
    @Mapping(target = "listAttendances", ignore = true)
    @Mapping(target = "listTeacherAssignments", ignore = true)
    Classroom toEntity(ClassroomRequest request);

    // --- toResponse (từ Entity) ---

    // 1. Ánh xạ tên từ các mối quan hệ Many-to-One
    @Mapping(source = "school.nameSchool", target = "schoolName")
    @Mapping(source = "homeroomTeacher.fullNameStaff", target = "homeroomTeacherName")

    // 2. Tính toán số lượng cho các trường tóm tắt (sử dụng tên chính xác từ Entity)
    @Mapping(target = "totalStudents",
            expression = "java(entity.getListStudents() != null ? entity.getListStudents().size() : 0)")
    @Mapping(target = "totalLessonLogs",
            expression = "java(entity.getListLessonLogs() != null ? entity.getListLessonLogs().size() : 0)")
    @Mapping(target = "totalAttendances",
            expression = "java(entity.getListAttendances() != null ? entity.getListAttendances().size() : 0)")
    @Mapping(target = "totalTeacherAssignments",
            expression = "java(entity.getListTeacherAssignments() != null ? entity.getListTeacherAssignments().size() : 0)")
    ClassroomResponse toResponse(Classroom entity);

    List<ClassroomResponse> toResponseList(List<Classroom> entities);
}

