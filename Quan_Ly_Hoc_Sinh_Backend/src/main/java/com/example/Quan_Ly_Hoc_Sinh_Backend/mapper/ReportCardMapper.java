package com.example.Quan_Ly_Hoc_Sinh_Backend.mapper;

import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.ReportCardDTOs.ReportCardRequest;
import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.ReportCardDTOs.ReportCardResponse;
import com.example.Quan_Ly_Hoc_Sinh_Backend.entity.ReportCard;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReportCardMapper {

    // --- toEntity (từ Request) ---
    // Ignore ID và tất cả các đối tượng Entity liên quan
    @Mapping(target = "idReportCard", ignore = true)
    @Mapping(target = "student", ignore = true)
    @Mapping(target = "semester", ignore = true)
    @Mapping(target = "conduct", ignore = true)
    ReportCard toEntity(ReportCardRequest request);

    // --- toResponse (từ Entity) ---

    // 1. Ánh xạ từ Student
    @Mapping(source = "student.idStudent", target = "studentId")
    @Mapping(source = "student.fullNameStudent", target = "studentFullName")

    // 2. Ánh xạ từ Semester
    @Mapping(source = "semester.idSemester", target = "semesterId")
    @Mapping(source = "semester.nameSemester", target = "semesterName")

    // 3. Ánh xạ từ Conduct (ĐÃ SỬA LỖI: Thay 'ratingConduct' bằng 'nameConduct')
    @Mapping(source = "conduct.idConduct", target = "conductId")
    @Mapping(source = "conduct.nameConduct", target = "conductRating") // <--- DÒNG BỊ LỖI ĐÃ ĐƯỢC SỬA

    ReportCardResponse toResponse(ReportCard entity);

    List<ReportCardResponse> toResponseList(List<ReportCard> entities);
}
