package com.example.Quan_Ly_Hoc_Sinh_Backend.controller;

import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.ScoreEntryDTOs.ScoreEntryRequest;
import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.ScoreEntryDTOs.ScoreEntryResponse;
import com.example.Quan_Ly_Hoc_Sinh_Backend.service.ScoreEntry.ScoreEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/scores")
public class ScoreEntryController {

    @Autowired
    private ScoreEntryService scoreEntryService;

    //API giáo viên nhập điểm mới cho học sinh

    @PostMapping("/add")
    public ResponseEntity<ScoreEntryResponse> createScore(@RequestBody ScoreEntryRequest request) {
        return ResponseEntity.ok(scoreEntryService.createScore(request));
    }

    @GetMapping("/student/{studentCode}/subject/{subjectCode}")
    public ResponseEntity<List<ScoreEntryResponse>> getScoresByCodes(
            @PathVariable String studentCode,
            @PathVariable String subjectCode) {

        List<ScoreEntryResponse> scores = scoreEntryService.getScoresByStudentCodeAndSubjectCode(studentCode, subjectCode);
        return ResponseEntity.ok(scores);
    }

}
