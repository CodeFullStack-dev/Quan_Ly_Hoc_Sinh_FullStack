package com.example.Quan_Ly_Hoc_Sinh_Backend.repository;

import com.example.Quan_Ly_Hoc_Sinh_Backend.model.Entity.ScoreEntry;
import com.example.Quan_Ly_Hoc_Sinh_Backend.model.Enum.EScoreType;
import com.example.Quan_Ly_Hoc_Sinh_Backend.model.Enum.ESemester;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScoreEntryRepository extends JpaRepository<ScoreEntry, Long> {
    // Tìm tất cả điểm của một học sinh cho một môn học
    List<ScoreEntry> findByStudentIdAndSubjectId(Long studentId, Long subjectId);

    // Tìm điểm của một học sinh theo loại kiểm tra
    List<ScoreEntry> findByStudentIdAndScoreType(Long studentId, EScoreType scoreType);

}
