package com.example.Quan_Ly_Hoc_Sinh_Backend.repository;

import com.example.Quan_Ly_Hoc_Sinh_Backend.model.Entity.GradeBook;
import com.example.Quan_Ly_Hoc_Sinh_Backend.model.Enum.ESemester;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GradeBookRepository extends JpaRepository<GradeBook, Long> {
    // Tìm điểm tổng kết duy nhất của một học sinh cho một môn trong một kỳ
    Optional<GradeBook> findByStudentIdAndSubjectIdAndSemester(Long studentId, Long subjectId, ESemester semester);

    // Lấy toàn bộ bảng điểm của một học sinh trong một năm
    List<GradeBook> findByStudentIdAndSchoolYear(Long studentId, String schoolYear);
}

