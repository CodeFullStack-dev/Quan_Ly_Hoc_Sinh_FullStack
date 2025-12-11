package com.example.Quan_Ly_Hoc_Sinh_Backend.repository;

import com.example.Quan_Ly_Hoc_Sinh_Backend.entity.Attendance;
import com.example.Quan_Ly_Hoc_Sinh_Backend.entity.Classroom;
import com.example.Quan_Ly_Hoc_Sinh_Backend.entity.Semester;
import com.example.Quan_Ly_Hoc_Sinh_Backend.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {
    // Lấy danh sách điểm danh theo Lớp và Ngày (cho GVCN/GV bộ môn)
    List<Attendance> findByClassroomAndDateAttendance(Classroom classroom, LocalDate dateAttendance);
    // Lấy lịch sử điểm danh của một Học sinh trong một Kỳ học
    List<Attendance> findByStudentAndSemester(Student student, Semester semester);
    // Kiểm tra 1 học sinh đã được điểm danh chưa.
    boolean existsByStudentAndDateAttendance(Student student, LocalDate dateAttendance);
    //Lấy điểm danh toàn kỳ của lớp.
    List<Attendance> findByClassroomAndSemester(Classroom classroom, Semester semester);
}
