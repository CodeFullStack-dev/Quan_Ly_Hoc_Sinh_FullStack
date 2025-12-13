package com.example.Quan_Ly_Hoc_Sinh_Backend.mapper;

import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.RoleDTOs.RoleRequest;
import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.RoleDTOs.RoleResponse;
import com.example.Quan_Ly_Hoc_Sinh_Backend.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

// unmappedTargetPolicy = IGNORE để tránh lỗi biên dịch
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleMapper {

    // --- toEntity (từ Request) ---
    @Mapping(target = "idRole", ignore = true)
    // Bỏ qua mối quan hệ One-to-Many
    @Mapping(target = "listUserRoles", ignore = true)
    Role toEntity(RoleRequest request);

    // --- toResponse (từ Entity) ---
    // Tính toán số lượng người dùng có Role này
    @Mapping(target = "totalUsers",
            expression = "java(entity.getListUserRoles() != null ? entity.getListUserRoles().size() : 0)")
    RoleResponse toResponse(Role entity);

    List<RoleResponse> toResponseList(List<Role> entities);
}
