package com.example.Quan_Ly_Hoc_Sinh_Backend.service.Student;

import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.StudentDTOs.StudentRequest;
import com.example.Quan_Ly_Hoc_Sinh_Backend.dto.StudentDTOs.StudentResponse;

import java.util.List;

public interface StudentService {
    StudentResponse createStudent(StudentRequest studentRequest);
    StudentResponse updateStudent(StudentRequest studentRequest);
    StudentResponse transferClass(Long studentId, Long newClassId);
    void deleteStudent(Long studentId);
    StudentResponse getStudentByCode(String studentCode);

    List<StudentResponse> searchStudents(String name, Long classId, String gender);
}
