package com.example.Quan_Ly_Hoc_Sinh_Backend.mapper;

import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.StaffDTOs.StaffRequest;
import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.StaffDTOs.StaffResponse;
import com.example.Quan_Ly_Hoc_Sinh_Backend.entity.Staff;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

public interface StaffMapper {

    // --- toEntity (từ Request) ---
    // Ignore các đối tượng Entity liên quan và các List One-to-Many
    @Mapping(target = "idStaff", ignore = true)
    @Mapping(target = "school", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "listTeacherAssignment", ignore = true)
    @Mapping(target = "listLessonLog", ignore = true)
    @Mapping(target = "listScoreRecord", ignore = true)
    @Mapping(target = "listHomeClasses", ignore = true) // Lớp chủ nhiệm
    Staff toEntity(StaffRequest request);

    // --- toResponse (từ Entity) ---

    // 1. Ánh xạ tên Trường và Username
    @Mapping(source = "school.nameSchool", target = "schoolName")
    @Mapping(source = "user.usernameUser", target = "username")

    // 2. Ánh xạ Danh sách Role (Ánh xạ phức tạp: User -> List<UserRole> -> Role -> NameRole)
    @Mapping(target = "currentRoles",
            expression = "java(entity.getUser() != null && entity.getUser().getListUserRoles() != null ? " +
                    "entity.getUser().getListUserRoles().stream()" +
                    ".map(ur -> ur.getRole().getNameRole())" +
                    ".collect(Collectors.toList()) : java.util.Collections.emptyList())")

    // 3. Ánh xạ Tên lớp chủ nhiệm (Lấy tên lớp đầu tiên trong List<Classroom>)
    @Mapping(target = "classroomName",
            expression = "java(entity.getListHomeClasses() != null && !entity.getListHomeClasses().isEmpty() ? " +
                    "entity.getListHomeClasses().get(0).getNameClassroom() : null)")

    // 4. Tính toán số lượng cho các trường tóm tắt (sử dụng tên chính xác từ Entity)
    @Mapping(target = "totalAssignments",
            expression = "java(entity.getListTeacherAssignment() != null ? entity.getListTeacherAssignment().size() : 0)")
    @Mapping(target = "totalLessonLogs",
            expression = "java(entity.getListLessonLog() != null ? entity.getListLessonLog().size() : 0)")
    @Mapping(target = "totalScoreRecords",
            expression = "java(entity.getListScoreRecord() != null ? entity.getListScoreRecord().size() : 0)")

    StaffResponse toResponse(Staff entity);

    List<StaffResponse> toResponseList(List<Staff> entities);
}