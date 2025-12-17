package com.example.Quan_Ly_Hoc_Sinh_Backend.repository;

import com.example.Quan_Ly_Hoc_Sinh_Backend.model.Entity.SchoolClass;
import com.example.Quan_Ly_Hoc_Sinh_Backend.model.Enum.EGradeLevel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SchoolClassRepository extends JpaRepository<SchoolClass, Long> {
    List<SchoolClass> findBySchoolIdAndSchoolYear(Long schoolId, String schoolYear);
    List<SchoolClass> findByGradeLevel(EGradeLevel gradeLevel);
}
