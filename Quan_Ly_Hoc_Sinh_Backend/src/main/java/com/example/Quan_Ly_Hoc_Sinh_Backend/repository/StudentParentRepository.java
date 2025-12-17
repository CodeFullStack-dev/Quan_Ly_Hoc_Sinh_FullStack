package com.example.Quan_Ly_Hoc_Sinh_Backend.repository;

import com.example.Quan_Ly_Hoc_Sinh_Backend.model.Entity.StudentParent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentParentRepository extends JpaRepository<StudentParent, Long> {
    // Tìm kiếm tất cả phụ huynh của một học sinh
    List<StudentParent> findByStudentId(Long studentId);

    // Tìm kiếm học sinh liên quan đến một phụ huynh
    List<StudentParent> findByParentId(Long parentId);
}
