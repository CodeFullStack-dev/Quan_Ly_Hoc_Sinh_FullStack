package com.example.Quan_Ly_Hoc_Sinh_Backend.mapper;


import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.StudentDTOs.StudentRequest;
import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.StudentDTOs.StudentResponse;
import com.example.Quan_Ly_Hoc_Sinh_Backend.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

// Sử dụng các Mapper phụ thuộc
@Mapper(componentModel = "spring",
        uses = {StudentCardMapper.class, StudentParentMapper.class},
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StudentMapper {

    // --- toEntity (từ Request) ---
    // Ignore các đối tượng Entity liên quan và các List One-to-Many
    @Mapping(target = "idStudent", ignore = true)
    @Mapping(target = "school", ignore = true)
    @Mapping(target = "classroom", ignore = true)
    @Mapping(target = "studentCard", ignore = true)
    @Mapping(target = "listStudentParents", ignore = true)
    @Mapping(target = "listScoreRecords", ignore = true)
    @Mapping(target = "listReportCards", ignore = true)
    @Mapping(target = "listAttendances", ignore = true)
    Student toEntity(StudentRequest request);

    // --- toResponse (từ Entity) ---

    // 1. Ánh xạ tên Trường, Lớp và Khối
    @Mapping(source = "school.nameSchool", target = "schoolName")
    @Mapping(source = "classroom.nameClassroom", target = "classroomName")
    @Mapping(source = "classroom.gradeLevelClassroom", target = "gradeLevel")

    // 2. Ánh xạ One-to-One (StudentCard)
    @Mapping(source = "studentCard", target = "studentCard")

    // 3. Ánh xạ One-to-Many (listStudentParents) - Sử dụng StudentParentMapper đã tạo
    @Mapping(source = "listStudentParents", target = "parents")

    // 4. Tính toán số lượng cho các trường tóm tắt (sử dụng tên chính xác từ Entity)
    @Mapping(target = "totalScoreRecords",
            expression = "java(entity.getListScoreRecords() != null ? entity.getListScoreRecords().size() : 0)")
    @Mapping(target = "totalReportCards",
            expression = "java(entity.getListReportCards() != null ? entity.getListReportCards().size() : 0)")
    @Mapping(target = "totalAttendances",
            expression = "java(entity.getListAttendances() != null ? entity.getListAttendances().size() : 0)")

    StudentResponse toResponse(Student entity);

    List<StudentResponse> toResponseList(List<Student> entities);
}
