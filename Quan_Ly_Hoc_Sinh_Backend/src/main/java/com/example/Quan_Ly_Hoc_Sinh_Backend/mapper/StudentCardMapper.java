package com.example.Quan_Ly_Hoc_Sinh_Backend.mapper;


import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.StudentCardDTOs.StudentCardRequest;
import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.StudentCardDTOs.StudentCardResponse;
import com.example.Quan_Ly_Hoc_Sinh_Backend.entity.StudentCard;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StudentCardMapper {

    // --- toEntity (từ Request) ---
    // Ignore đối tượng Entity Student
    @Mapping(target = "idStudentCard", ignore = true)
    @Mapping(target = "student", ignore = true)
    StudentCard toEntity(StudentCardRequest request);

    // --- toResponse (từ Entity) ---

    // Ánh xạ ID và Tên từ đối tượng Student (One-to-One)
    @Mapping(source = "student.idStudent", target = "studentId")
    @Mapping(source = "student.fullNameStudent", target = "studentFullName")

    StudentCardResponse toResponse(StudentCard entity);

    List<StudentCardResponse> toResponseList(List<StudentCard> entities);
}
