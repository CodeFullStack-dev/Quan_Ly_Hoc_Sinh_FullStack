package com.example.Quan_Ly_Hoc_Sinh_Backend.repository;

import com.example.Quan_Ly_Hoc_Sinh_Backend.entity.Classroom;
import com.example.Quan_Ly_Hoc_Sinh_Backend.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    Optional<Student> findByCodeStudent(String codeStudent);

    List<Student> findByClassroom(Classroom classroom); // Lấy danh sách học sinh theo Lớp học
}
