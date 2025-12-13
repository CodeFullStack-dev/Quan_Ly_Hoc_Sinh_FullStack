package com.example.Quan_Ly_Hoc_Sinh_Backend.mapper;


import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.AttendanceDTOs.AttendanceRequest;
import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.AttendanceDTOs.AttendanceResponse;
import com.example.Quan_Ly_Hoc_Sinh_Backend.entity.Attendance;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AttendanceMapper {

    // --- toEntity (từ Request) ---
    // Ignore ID và tất cả các đối tượng Entity liên quan
    @Mapping(target = "idAttendance", ignore = true)
    @Mapping(target = "student", ignore = true)
    @Mapping(target = "classroom", ignore = true)
    @Mapping(target = "semester", ignore = true)
    Attendance toEntity(AttendanceRequest request);

    // --- toResponse (từ Entity) ---

    // 1. Ánh xạ từ Student
    @Mapping(source = "student.idStudent", target = "studentId")
    @Mapping(source = "student.fullNameStudent", target = "studentFullName")

    // 2. Ánh xạ từ Classroom
    @Mapping(source = "classroom.idClassroom", target = "classroomId")
    @Mapping(source = "classroom.nameClassroom", target = "classroomName")

    // 3. Ánh xạ từ Semester
    @Mapping(source = "semester.idSemester", target = "semesterId")
    @Mapping(source = "semester.nameSemester", target = "semesterName")

    AttendanceResponse toResponse(Attendance entity);

    List<AttendanceResponse> toResponseList(List<Attendance> entities);
}
