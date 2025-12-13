package com.example.Quan_Ly_Hoc_Sinh_Backend.mapper;


import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.ScoreTypeDTOs.ScoreTypeRequest;
import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.ScoreTypeDTOs.ScoreTypeResponse;
import com.example.Quan_Ly_Hoc_Sinh_Backend.entity.ScoreType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ScoreTypeMapper {

    // --- toEntity (từ Request) ---
    @Mapping(target = "idScoreType", ignore = true)
    // Bỏ qua mối quan hệ One-to-Many
    @Mapping(target = "listScoreRecords", ignore = true)
    ScoreType toEntity(ScoreTypeRequest request);

    // --- toResponse (từ Entity) ---
    // Tính toán số lượng bản ghi điểm sử dụng loại này
    @Mapping(target = "totalScoreRecords",
            expression = "java(entity.getListScoreRecords() != null ? entity.getListScoreRecords().size() : 0)")
    ScoreTypeResponse toResponse(ScoreType entity);

    List<ScoreTypeResponse> toResponseList(List<ScoreType> entities);
}
