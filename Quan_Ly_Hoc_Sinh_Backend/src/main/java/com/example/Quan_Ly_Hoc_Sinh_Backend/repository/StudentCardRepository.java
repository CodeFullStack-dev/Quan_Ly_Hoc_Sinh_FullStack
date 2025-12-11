package com.example.Quan_Ly_Hoc_Sinh_Backend.repository;

import com.example.Quan_Ly_Hoc_Sinh_Backend.entity.Student;
import com.example.Quan_Ly_Hoc_Sinh_Backend.entity.StudentCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentCardRepository extends JpaRepository<StudentCard, Integer> {
    Optional<StudentCard> findByStudent(Student student);
}
