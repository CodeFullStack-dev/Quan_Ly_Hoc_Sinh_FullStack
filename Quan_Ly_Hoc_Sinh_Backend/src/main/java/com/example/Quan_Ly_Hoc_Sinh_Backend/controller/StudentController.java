package com.example.Quan_Ly_Hoc_Sinh_Backend.controller;

import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.StudentDTOs.StudentRequest;
import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.StudentDTOs.StudentResponse;
import com.example.Quan_Ly_Hoc_Sinh_Backend.service.Student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    //Tạo mới học sinh
    @PostMapping("/create")
    public ResponseEntity<StudentResponse> createStudent(@RequestBody StudentRequest request) {
        return ResponseEntity.ok(studentService.createStudent(request));
    }

    //Cập nhật thông tin các nhân (PUT)
    @PutMapping("/update/{id}")
    public ResponseEntity<StudentResponse> update(@RequestBody StudentRequest request) {
        return ResponseEntity.ok(studentService.updateStudent(request));
    }

    //Chuyển lớp
    // PATCH thường dùng cho việc cập nhật một phần dữ liệu (ở đây là Class)
    @PatchMapping("/{studentId}/tranfer/{newClassId}")
    public ResponseEntity<StudentResponse> transferClass(
            @PathVariable Long studentId,
            @PathVariable Long newClassId
    ) {
        return ResponseEntity.ok(studentService.transferClass(studentId, newClassId));
    }

    // Xóa học sinh
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok("Đã xóa học sinh thành công");
    }

    @GetMapping("/{studentCode}")
    public ResponseEntity<StudentResponse> getByCode(@PathVariable String studentCode) {
        return ResponseEntity.ok(studentService.getStudentByCode(studentCode));
    }

    @GetMapping("/search")
    public ResponseEntity<List<StudentResponse>> search(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Long classId,
            @RequestParam(required = false) String gender
    ) {
        return ResponseEntity.ok(studentService.searchStudents(name, classId, gender));
    }
}
