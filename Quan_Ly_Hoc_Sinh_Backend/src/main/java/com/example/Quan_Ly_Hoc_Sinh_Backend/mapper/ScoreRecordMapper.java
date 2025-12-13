package com.example.Quan_Ly_Hoc_Sinh_Backend.mapper;


import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.ScoreRecordDTOs.ScoreRecordRequest;
import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.ScoreRecordDTOs.ScoreRecordResponse;
import com.example.Quan_Ly_Hoc_Sinh_Backend.entity.ScoreRecord;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ScoreRecordMapper {

    // --- toEntity (từ Request) ---
    // Ignore ID và tất cả các đối tượng Entity liên quan
    @Mapping(target = "idScoreRecord", ignore = true)
    @Mapping(target = "student", ignore = true)
    @Mapping(target = "teacher", ignore = true)
    @Mapping(target = "semester", ignore = true)
    @Mapping(target = "scoreType", ignore = true)
    @Mapping(target = "subject", ignore = true)
    ScoreRecord toEntity(ScoreRecordRequest request);

    // --- toResponse (từ Entity) ---

    // 1. Ánh xạ từ Student
    @Mapping(source = "student.idStudent", target = "studentId")
    @Mapping(source = "student.fullNameStudent", target = "studentFullName")

    // 2. Ánh xạ từ Teacher (Staff)
    @Mapping(source = "teacher.idStaff", target = "teacherId")
    @Mapping(source = "teacher.fullNameStaff", target = "teacherFullName")

    // 3. Ánh xạ từ Semester
    @Mapping(source = "semester.idSemester", target = "semesterId")
    @Mapping(source = "semester.nameSemester", target = "semesterName")

    // 4. Ánh xạ từ ScoreType (Giả định ScoreType có trường 'nameScoreType')
    @Mapping(source = "scoreType.idScoreType", target = "scoreTypeId")
    @Mapping(source = "scoreType.nameScoreType", target = "scoreTypeName")

    // 5. Ánh xạ từ Subject
    @Mapping(source = "subject.idSubject", target = "subjectId")
    @Mapping(source = "subject.nameSubject", target = "subjectName")

    ScoreRecordResponse toResponse(ScoreRecord entity);

    List<ScoreRecordResponse> toResponseList(List<ScoreRecord> entities);
}
