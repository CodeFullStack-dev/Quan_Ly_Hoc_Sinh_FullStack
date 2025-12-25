package com.example.Quan_Ly_Hoc_Sinh_Backend.service.ScoreEntry;

import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.ScoreEntryDTOs.ScoreEntryRequest;
import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.ScoreEntryDTOs.ScoreEntryResponse;
import com.example.Quan_Ly_Hoc_Sinh_Backend.model.Enum.ESemester;

import java.util.List;

public interface ScoreEntryService {
    ScoreEntryResponse createScore(ScoreEntryRequest request);
    ScoreEntryResponse updateScore(Long id, ScoreEntryRequest request);
    void deleteScore(Long id, ESemester semester, String schoolYear);

    // Tìm theo ID (Long)
    List<ScoreEntryResponse> getScoresByStudentAndSubject(Long studentId, Long subjectId);

    // THÊM MỚI: Tìm theo Mã (String)
    List<ScoreEntryResponse> getScoresByStudentCodeAndSubjectCode(String studentCode, String subjectCode);
}