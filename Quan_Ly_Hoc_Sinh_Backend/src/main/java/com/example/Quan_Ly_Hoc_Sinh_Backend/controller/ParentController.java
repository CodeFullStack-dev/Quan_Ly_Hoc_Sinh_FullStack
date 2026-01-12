package com.example.Quan_Ly_Hoc_Sinh_Backend.controller;

import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.ParentDTOs.ParentRequest;
import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.ParentDTOs.ParentResponse;
import com.example.Quan_Ly_Hoc_Sinh_Backend.service.Parent.ParentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parents")
public class ParentController {

    @Autowired
    private ParentService parentService;

    @PostMapping("/add")
    public ResponseEntity<?> addParentToStudent(@RequestBody ParentRequest request) {
        try {
            ParentResponse response = parentService.addParentToStudent(request);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Lỗi: Loại quan hệ (Relationship) không hợp lệ!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi hệ thống: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParentResponse> updateParent(
            @PathVariable Long id,
            @RequestBody ParentRequest request) {
        return ResponseEntity.ok(parentService.updateParent(id, request));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ParentResponse>> getAllParents(@RequestParam(required = false) String query) {
        return ResponseEntity.ok(parentService.getAllParentsInfo(query));
    }
}
