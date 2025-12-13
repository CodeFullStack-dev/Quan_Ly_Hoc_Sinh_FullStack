package com.example.Quan_Ly_Hoc_Sinh_Backend.mapper;

import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.LessonLogDTOs.LessonLogRequest;
import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.LessonLogDTOs.LessonLogResponse;
import com.example.Quan_Ly_Hoc_Sinh_Backend.entity.LessonLog;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LessonLogMapper {

    // --- toEntity (từ Request) ---
    // Ignore ID và tất cả các đối tượng Entity liên quan
    @Mapping(target = "idLessonLog", ignore = true)
    @Mapping(target = "classroom", ignore = true)
    @Mapping(target = "subject", ignore = true)
    @Mapping(target = "teacher", ignore = true)
    @Mapping(target = "semester", ignore = true)
    LessonLog toEntity(LessonLogRequest request);

    // --- toResponse (từ Entity) ---

    // 1. Ánh xạ từ Classroom
    @Mapping(source = "classroom.idClassroom", target = "classroomId")
    @Mapping(source = "classroom.nameClassroom", target = "classroomName")

    // 2. Ánh xạ từ Subject
    @Mapping(source = "subject.idSubject", target = "subjectId")
    @Mapping(source = "subject.nameSubject", target = "subjectName")

    // 3. Ánh xạ từ Teacher (Staff)
    @Mapping(source = "teacher.idStaff", target = "teacherId")
    @Mapping(source = "teacher.fullNameStaff", target = "teacherFullName")

    // 4. Ánh xạ từ Semester
    @Mapping(source = "semester.idSemester", target = "semesterId")
    @Mapping(source = "semester.nameSemester", target = "semesterName")

    LessonLogResponse toResponse(LessonLog entity);

    List<LessonLogResponse> toResponseList(List<LessonLog> entities);
}
