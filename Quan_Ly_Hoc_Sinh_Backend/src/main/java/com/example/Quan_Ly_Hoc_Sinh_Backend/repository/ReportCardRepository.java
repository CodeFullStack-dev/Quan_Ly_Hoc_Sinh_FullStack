package com.example.Quan_Ly_Hoc_Sinh_Backend.repository;

import com.example.Quan_Ly_Hoc_Sinh_Backend.entity.ReportCard;
import com.example.Quan_Ly_Hoc_Sinh_Backend.entity.Semester;
import com.example.Quan_Ly_Hoc_Sinh_Backend.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReportCardRepository extends JpaRepository<ReportCard, Integer> {
    // Lấy sổ học bạ duy nhất cho một HS trong một HK
    Optional<ReportCard> findByStudentAndSemester(Student student, Semester semester);
}
