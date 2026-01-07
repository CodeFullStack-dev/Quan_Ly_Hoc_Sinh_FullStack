package com.example.Quan_Ly_Hoc_Sinh_Backend.service.LessonLog;

import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.LessonLogDTOs.LessonLogRequest;
import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.LessonLogDTOs.LessonLogResponse;

import java.util.List;

public interface LessonLogService {
    LessonLogResponse createLog(LessonLogRequest requeste);
    List<LessonLogResponse> getLogsByClass(Long classId);
    void deleteLog(Long id);
}
