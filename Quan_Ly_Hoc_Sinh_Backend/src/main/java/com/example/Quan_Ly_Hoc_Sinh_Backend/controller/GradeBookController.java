package com.example.Quan_Ly_Hoc_Sinh_Backend.controller;

import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.GradeBookDTOs.GradeBookRequest;
import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.GradeBookDTOs.GradeBookResponse;
import com.example.Quan_Ly_Hoc_Sinh_Backend.repository.GradeBookRepository;
import com.example.Quan_Ly_Hoc_Sinh_Backend.service.GradeBook.GradeBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grade-books")
public class GradeBookController {
    @Autowired
    private GradeBookRepository gradeBookRepository;
    @Autowired
    private GradeBookService gradeBookService;

    // API: Xem bảng điểm tổng kết của một học sinh trong một năm học cụ thể
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<GradeBookResponse>> getAllGradeBooks(
            @PathVariable Long studentId,
            @RequestParam String schoolYear
            ) {
        return ResponseEntity.ok(gradeBookService.getStudentGradeBook(studentId, schoolYear));

    }

    @PostMapping("/manual-save")
    public ResponseEntity<GradeBookResponse> saveManual(@RequestBody GradeBookRequest request) {
        return ResponseEntity.ok(gradeBookService.saveManual(request));
    }
}
