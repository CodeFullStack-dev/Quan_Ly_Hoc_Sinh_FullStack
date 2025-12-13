package com.example.Quan_Ly_Hoc_Sinh_Backend.mapper;

import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.ParentDTOs.ParentRequest;
import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.ParentDTOs.ParentResponse;
import com.example.Quan_Ly_Hoc_Sinh_Backend.entity.Parent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

// Đảm bảo sử dụng StudentParentMapper cho mối quan hệ List<StudentParent>
@Mapper(componentModel = "spring",
        uses = {StudentParentMapper.class},
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ParentMapper {

    // --- toEntity (từ Request) ---
    // Ignore đối tượng Entity User và List One-to-Many
    @Mapping(target = "idParent", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "listStudentParents", ignore = true)
    Parent toEntity(ParentRequest request);

    // --- toResponse (từ Entity) ---

    // 1. Ánh xạ Username từ User
    @Mapping(source = "user.usernameUser", target = "username")

    // 2. Ánh xạ List<StudentParent> sang List<StudentParentResponse>
    @Mapping(source = "listStudentParents", target = "studentRelations")

    ParentResponse toResponse(Parent entity);

    List<ParentResponse> toResponseList(List<Parent> entities);
}