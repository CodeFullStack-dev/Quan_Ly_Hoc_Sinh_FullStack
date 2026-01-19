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

    // 1. TẠO MỚI (Dùng POST /academic-records)
    @PostMapping("/create")
    public ResponseEntity<AcademicRecordResponse> create(@RequestBody AcademicRecordRequest request) {
        return ResponseEntity.ok(academicRecordService.createAcademicRecord(request));
    }

    // 2. CẬP NHẬT (Dùng PUT /academic-records/update)
    @PutMapping("/update")
    public ResponseEntity<AcademicRecordResponse> update(@RequestBody AcademicRecordRequest request) {
        return ResponseEntity.ok(academicRecordService.updateAcademicRecord(request));
    }

    // 3. ĐỒNG BỘ ĐIỂM (Dùng POST /academic-records/sync)
    @PostMapping("/sync")
    public ResponseEntity<AcademicRecordResponse> sync(@RequestBody AcademicRecordRequest request) {
        return ResponseEntity.ok(academicRecordService.syncGrades(request));
    }

    // Tìm lịch sử theo mã học sinh: /academic-records/history/HS001
    @GetMapping("/history/{studentCode}")
    public ResponseEntity<List<AcademicRecordResponse>> getHistory(@PathVariable String studentCode) {
        return ResponseEntity.ok(academicRecordService.getStudentHistory(studentCode));
    }

    // Tìm chi tiết: /academic-records/detail/HS001/2025-2026
    @GetMapping("/detail/{studentCode}/{schoolYear}")
    public ResponseEntity<AcademicRecordResponse> getOne(
            @PathVariable String studentCode,
            @PathVariable String schoolYear) {
        return ResponseEntity.ok(academicRecordService.getRecordByStudentCodeAndYear(studentCode, schoolYear));
    }

    // Xóa: /academic-records/delete/HS001/2025-2026
    @DeleteMapping("/delete/{studentCode}/{schoolYear}")
    public ResponseEntity<String> delete(
            @PathVariable String studentCode,
            @PathVariable String schoolYear) {
        academicRecordService.deleteRecordByCode(studentCode, schoolYear);
        return ResponseEntity.ok("Xóa học bạ thành công");
    }
}