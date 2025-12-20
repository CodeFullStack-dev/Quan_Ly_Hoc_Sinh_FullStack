package com.example.Quan_Ly_Hoc_Sinh_Backend.controller;


import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.SchoolDTOs.SchoolRequest;
import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.SchoolDTOs.SchoolResponse;
import com.example.Quan_Ly_Hoc_Sinh_Backend.service.School.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schools")
public class SchoolController {
    @Autowired
    private SchoolService schoolService;

    @GetMapping
    public ResponseEntity<List<SchoolResponse>> getAll() {
        return ResponseEntity.ok(schoolService.getAllSchools());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SchoolResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(schoolService.getSchoolById(id));
    }

    @PostMapping
    public ResponseEntity<SchoolResponse> createSchool(@RequestBody SchoolRequest request) {
        return ResponseEntity.ok(schoolService.createSchool(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SchoolResponse> update(@PathVariable Long id, @RequestBody SchoolRequest request) {
        return ResponseEntity.ok(schoolService.updateSchool(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        schoolService.deleteSchool(id);
        return ResponseEntity.noContent().build();
    }
}
