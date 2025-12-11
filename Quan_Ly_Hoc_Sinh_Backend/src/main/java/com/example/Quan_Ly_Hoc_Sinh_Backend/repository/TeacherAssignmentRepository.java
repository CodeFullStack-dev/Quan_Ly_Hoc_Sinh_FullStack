package com.example.Quan_Ly_Hoc_Sinh_Backend.repository;

import com.example.Quan_Ly_Hoc_Sinh_Backend.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeacherAssignmentRepository extends JpaRepository<TeacherAssignment, Integer> {
    List<TeacherAssignment> findByTeacherAndSemester(Staff teacher, Semester semester); // Lọc các phân công của Giáo viên trong một Học kỳ

    // Lấy phân công cụ thể (để kiểm tra quyền nhập điểm/ghi sổ)
    Optional<TeacherAssignment> findByClassroomAndSubjectAndSemester(Classroom classroom, Subject subject, Semester semester);
}
