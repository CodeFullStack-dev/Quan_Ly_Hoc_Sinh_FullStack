package com.example.Quan_Ly_Hoc_Sinh_Backend.controller;

import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.AcademicRecordDTOs.AcademicRecordRequest;
import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.AcademicRecordDTOs.AcademicRecordResponse;
import com.example.Quan_Ly_Hoc_Sinh_Backend.service.AcademicRecord.AcademicRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/academic-records")
public class AcademicRecordController {

    @Autowired
    private AcademicRecordService academicRecordService;

    // Tạo hoặc Cập nhật (Sync)
    @PostMapping("/sync")
    public ResponseEntity<AcademicRecordResponse> saveOrUpdate(@RequestBody AcademicRecordRequest request) {
        return ResponseEntity.ok(academicRecordService.generateOrUpdateRecord(request));
    }

    // Tìm kiếm lịch sử học tập của 1 học sinh
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<AcademicRecordResponse>> getHistory(@PathVariable Long studentId) {
        return ResponseEntity.ok(academicRecordService.getStudentHistory(studentId));
    }

    // Tìm kiếm học bạ cụ thể theo năm
    @GetMapping("/search/{studentId}/{schoolYear}")
    public ResponseEntity<AcademicRecordResponse> getOne(
            @PathVariable Long studentId,
            @PathVariable String schoolYear) {
        return ResponseEntity.ok(academicRecordService.getRecordByStudentAndYear(studentId, schoolYear));
    }

    // Xóa học bạ
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        academicRecordService.deleteRecord(id);
        return ResponseEntity.ok("Xóa học bạ thành công");
    }
}
