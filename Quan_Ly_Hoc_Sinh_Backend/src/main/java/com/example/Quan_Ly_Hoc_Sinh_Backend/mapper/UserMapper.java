package com.example.Quan_Ly_Hoc_Sinh_Backend.mapper;

import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.User.UserRequest;
import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.User.UserResponse;
import com.example.Quan_Ly_Hoc_Sinh_Backend.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    // --- toEntity (từ Request) ---
    @Mapping(target = "idUser", ignore = true)
    @Mapping(target = "listUserRoles", ignore = true)
    User toEntity(UserRequest request);

    // --- toResponse (từ Entity) ---

    // Ánh xạ phức tạp: SỬ DỤNG FQN CHO COLLECTORS
    @Mapping(target = "currentRoles",
            expression = "java(entity.getListUserRoles() != null ? " +
                    "entity.getListUserRoles().stream()" +
                    ".map(userRole -> userRole.getRole().getNameRole())" +
                    ".collect(java.util.stream.Collectors.toList()) : java.util.Collections.emptyList())")
    UserResponse toResponse(User entity);

    List<UserResponse> toResponseList(List<User> entities);
}