package com.example.Quan_Ly_Hoc_Sinh_Backend.repository;

import com.example.Quan_Ly_Hoc_Sinh_Backend.entity.Classroom;
import com.example.Quan_Ly_Hoc_Sinh_Backend.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassroomRepository extends JpaRepository<Classroom, Integer> {

    List<Classroom> findByGradeLevelClassroomAndAcademicYearClassroom(String gradeLevel, String academicYear); // Lọc lớp theo khối và niên khóa (Ví dụ: Khối 10, 2025-2026)

    List<Classroom> findByHomeroomTeacher(Staff homeroomTeacher); // Tìm các lớp do một Staff chủ nhiệm

    //Tìm lớp theo khối
    List<Classroom> findByGradeLevelClassroom(String gradeLevelClassroom);

}
