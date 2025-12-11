package com.example.Quan_Ly_Hoc_Sinh_Backend.repository;

import com.example.Quan_Ly_Hoc_Sinh_Backend.entity.Classroom;
import com.example.Quan_Ly_Hoc_Sinh_Backend.entity.LessonLog;
import com.example.Quan_Ly_Hoc_Sinh_Backend.entity.Semester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LessonLogRepository extends JpaRepository<LessonLog, Integer> {
    // Lấy tất cả sổ đầu bài của một Lớp trong một Học kỳ
    List<LessonLog> findByClassroomAndSemester(Classroom classroom, Semester semester);

    //Lấy theo ngày
    List<LessonLog> findByClassroomAndLessonDateLessonLog(Classroom classroom, LocalDate date);

}
