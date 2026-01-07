package com.example.Quan_Ly_Hoc_Sinh_Backend.controller;

import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.LessonLogDTOs.LessonLogRequest;
import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.LessonLogDTOs.LessonLogResponse;
import com.example.Quan_Ly_Hoc_Sinh_Backend.service.LessonLog.LessonLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lesson-logs")
public class LessonLogController {

    @Autowired
    private LessonLogService lessonLogService;

    // 1. Giáo viên tạo dòng sổ đầu bài mới
    @PostMapping("/create")
    public ResponseEntity<LessonLogResponse> create(@RequestBody LessonLogRequest request) {
        return ResponseEntity.ok(lessonLogService.createLog(request));
    }

    // 2. Lấy toàn bộ sổ đầu bài của một lớp (để hiển thị lên bảng)
    @GetMapping("/class/{classId}")
    public ResponseEntity<List<LessonLogResponse>> getByClass(@PathVariable Long classId) {
        return ResponseEntity.ok(lessonLogService.getLogsByClass(classId));
    }

    // 3. Xóa một dòng sổ đầu bài (dành cho Admin hoặc Giáo vụ khi nhập sai)
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        lessonLogService.deleteLog(id);
        return ResponseEntity.ok("Xóa dòng sổ đầu bài thành công");
    }
}
