package com.example.Quan_Ly_Hoc_Sinh_Backend.repository;

import com.example.Quan_Ly_Hoc_Sinh_Backend.model.Entity.LessonLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface LessonLogRepository extends JpaRepository<LessonLog, Long> {
    // Lấy tất cả sổ đầu bài của một lớp trong một khoảng thời gian
    List<LessonLog> findBySchoolClassIdAndLessonDateBetween(Long classId, Date startDate, Date endDate);

    // Lấy tất cả bài giảng của một giáo viên
    List<LessonLog> findByTeacherId(Long teacherId);
}
