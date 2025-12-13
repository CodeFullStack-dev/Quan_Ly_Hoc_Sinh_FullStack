package com.example.Quan_Ly_Hoc_Sinh_Backend.mapper;

import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.ConductDTOs.ConductRequest;
import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.ConductDTOs.ConductResponse;
import com.example.Quan_Ly_Hoc_Sinh_Backend.entity.Conduct;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ConductMapper {

    // --- toEntity (từ Request) ---
    @Mapping(target = "idConduct", ignore = true)
    // Bỏ qua mối quan hệ One-to-Many
    @Mapping(target = "listReportCards", ignore = true)
    Conduct toEntity(ConductRequest request);

    // --- toResponse (từ Entity) ---
    // Tính toán số lượng ReportCard sử dụng mức xếp loại này
    @Mapping(target = "totalReportCards",
            expression = "java(entity.getListReportCards() != null ? entity.getListReportCards().size() : 0)")
    ConductResponse toResponse(Conduct entity);

    List<ConductResponse> toResponseList(List<Conduct> entities);
}
