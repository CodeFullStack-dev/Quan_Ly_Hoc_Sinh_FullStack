package com.example.Quan_Ly_Hoc_Sinh_Backend.mapper;

import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.UserRoleDTOs.UserRoleRequest;
import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.UserRoleDTOs.UserRoleResponse;
import com.example.Quan_Ly_Hoc_Sinh_Backend.entity.UserRole;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserRoleMapper {

    // --- toEntity (từ Request) ---
    // Các trường Entity đối tượng (User, Role) phải được ignore
    @Mapping(target = "idUserRole", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "role", ignore = true)
    UserRole toEntity(UserRoleRequest request);

    // --- toResponse (từ Entity) ---

    // 1. Ánh xạ ID và Tên từ đối tượng User
    @Mapping(source = "user.idUser", target = "userId")
    @Mapping(source = "user.usernameUser", target = "username")

    // 2. Ánh xạ ID và Tên từ đối tượng Role
    @Mapping(source = "role.idRole", target = "roleId")
    @Mapping(source = "role.nameRole", target = "roleName")

    UserRoleResponse toResponse(UserRole entity);

    List<UserRoleResponse> toResponseList(List<UserRole> entities);
}
