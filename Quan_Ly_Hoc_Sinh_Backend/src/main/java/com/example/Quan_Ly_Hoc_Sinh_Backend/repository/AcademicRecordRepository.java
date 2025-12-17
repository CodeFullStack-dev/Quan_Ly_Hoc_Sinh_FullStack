package com.example.Quan_Ly_Hoc_Sinh_Backend.repository;

import com.example.Quan_Ly_Hoc_Sinh_Backend.model.Entity.AcademicRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AcademicRecordRepository extends JpaRepository<AcademicRecord, Long> {
    // Tìm học bạ duy nhất của học sinh trong một niên khóa
    Optional<AcademicRecord> findByStudentIdAndSchoolYear(Long studentId, String schoolYear);
}
