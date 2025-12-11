package com.example.Quan_Ly_Hoc_Sinh_Backend.repository;

import com.example.Quan_Ly_Hoc_Sinh_Backend.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreRecordRepository extends JpaRepository<ScoreRecord, Integer> {
    // Lấy tất cả điểm của một HS cho một Môn trong một HK (để tính điểm TB)
    List<ScoreRecord> findByStudentAndSubjectAndSemester(Student student, Subject subject, Semester semester);
    // Lọc điểm theo giáo viên nhập (để kiểm tra quyền chỉnh sửa)
    List<ScoreRecord> findByTeacher(Staff teacher);
}
