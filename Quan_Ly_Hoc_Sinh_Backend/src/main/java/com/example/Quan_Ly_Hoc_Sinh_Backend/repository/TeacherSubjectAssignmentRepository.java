package com.example.Quan_Ly_Hoc_Sinh_Backend.repository;

import com.example.Quan_Ly_Hoc_Sinh_Backend.model.Entity.TeacherSubjectAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TeacherSubjectAssignmentRepository extends JpaRepository<TeacherSubjectAssignment, Long> {
    // Kiểm tra xem một giáo viên có phụ trách môn học đó trong năm học đó không
    Optional<TeacherSubjectAssignment> findByTeacherIdAndSubjectIdAndSchoolYear(
            Long teacherId, Long subjectId, String schoolYear
    );

    // Lấy tất cả môn học mà một giáo viên phụ trách
    List<TeacherSubjectAssignment> findByTeacherId(Long teacherId);
}
