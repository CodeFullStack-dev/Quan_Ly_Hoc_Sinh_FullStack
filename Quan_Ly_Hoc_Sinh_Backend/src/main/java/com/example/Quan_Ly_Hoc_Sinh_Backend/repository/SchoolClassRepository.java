package com.example.Quan_Ly_Hoc_Sinh_Backend.repository;

import com.example.Quan_Ly_Hoc_Sinh_Backend.model.Entity.SchoolClass;
import com.example.Quan_Ly_Hoc_Sinh_Backend.model.Enum.EGradeLevel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SchoolClassRepository extends JpaRepository<SchoolClass, Long> {
    Optional<SchoolClass> findByClassNameAndSchoolYear(String className, String schoolYear);
    List<SchoolClass> findBySchoolIdAndSchoolYear(Long schoolId, String schoolYear);
    List<SchoolClass> findByGradeLevel(EGradeLevel gradeLevel);
    // Kiểm tra xem giáo viên đã chủ nhiệm lớp nào chưa
    boolean existsByHomeroomTeacherId(Long teacherId);
}
