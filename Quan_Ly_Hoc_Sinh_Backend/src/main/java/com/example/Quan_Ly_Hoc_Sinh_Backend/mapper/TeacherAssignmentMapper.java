package com.example.Quan_Ly_Hoc_Sinh_Backend.mapper;

import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.TeacherAssignmentDTOs.TeacherAssignmentRequest;
import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.TeacherAssignmentDTOs.TeacherAssignmentResponse;
import com.example.Quan_Ly_Hoc_Sinh_Backend.entity.TeacherAssignment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TeacherAssignmentMapper {

    // --- toEntity (từ Request) ---
    // Ignore ID và tất cả các đối tượng Entity liên quan
    @Mapping(target = "id_teacher_assignment", ignore = true) // Sửa tên trường ID trong Entity
    @Mapping(target = "teacher", ignore = true)
    @Mapping(target = "subject", ignore = true)
    @Mapping(target = "classroom", ignore = true)
    @Mapping(target = "semester", ignore = true)
    TeacherAssignment toEntity(TeacherAssignmentRequest request);

    // --- toResponse (từ Entity) ---

    // 1. Ánh xạ từ Teacher (Staff)
    @Mapping(source = "teacher.idStaff", target = "teacherId")
    @Mapping(source = "teacher.fullNameStaff", target = "teacherFullName")

    // 2. Ánh xạ từ Subject
    @Mapping(source = "subject.idSubject", target = "subjectId")
    @Mapping(source = "subject.nameSubject", target = "subjectName")

    // 3. Ánh xạ từ Classroom
    @Mapping(source = "classroom.idClassroom", target = "classroomId")
    @Mapping(source = "classroom.nameClassroom", target = "classroomName")
    @Mapping(source = "classroom.gradeLevelClassroom", target = "classroomGradeLevel")

    // 4. Ánh xạ từ Semester
    @Mapping(source = "semester.idSemester", target = "semesterId")
    @Mapping(source = "semester.nameSemester", target = "semesterName")

    TeacherAssignmentResponse toResponse(TeacherAssignment entity);

    List<TeacherAssignmentResponse> toResponseList(List<TeacherAssignment> entities);
}
