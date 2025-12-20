package com.example.Quan_Ly_Hoc_Sinh_Backend.controller;

import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.SchoolClassDTOs.SchoolClassRequest;
import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.SchoolClassDTOs.SchoolClassResponse;
import com.example.Quan_Ly_Hoc_Sinh_Backend.service.SchoolClass.SchoolClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/classes")
public class SchoolClassController {

    @Autowired
    private SchoolClassService schoolClassService;

    @PostMapping("/create")
    public ResponseEntity<SchoolClassResponse> create(@RequestBody SchoolClassRequest request) {
        return ResponseEntity.ok(schoolClassService.createClass(request));
    }

    @GetMapping("/all")
    public ResponseEntity<List<SchoolClassResponse>> getAll() {
        return ResponseEntity.ok(schoolClassService.getAllClasses());
    }
}
