package com.example.Quan_Ly_Hoc_Sinh_Backend.controller;

import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.StudentCardDTOs.StudentCardResponse;
import com.example.Quan_Ly_Hoc_Sinh_Backend.service.StudentCard.StudentCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/student-card")
@RequiredArgsConstructor
public class StudentCardController {

    @Autowired
    private final StudentCardService studentCardService;

    @PostMapping(value = "/issue", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<StudentCardResponse> issueCard(
            @RequestParam("studentCode") String studentCode,
            @RequestPart("file") MultipartFile file) {
        return ResponseEntity.ok(studentCardService.issueCard(studentCode, file));
    }

    @GetMapping("/{studentCode}")
    public ResponseEntity<StudentCardResponse> getCard(@PathVariable String studentCode) {
        return ResponseEntity.ok(studentCardService.getCardByStudentCode(studentCode));
    }

    @PatchMapping("/status/{studentCode}")
    public ResponseEntity<StudentCardResponse> updateStatus(
            @PathVariable String studentCode,
            @RequestParam String status) {
        return ResponseEntity.ok(studentCardService.updateCardStatus(studentCode, status));
    }

    @DeleteMapping("/{studentCode}")
    public ResponseEntity<String> delete(@PathVariable String studentCode) {
        studentCardService.deleteCard(studentCode);
        return ResponseEntity.ok("Đã xóa thẻ và ảnh liên quan thành công");
    }
}
