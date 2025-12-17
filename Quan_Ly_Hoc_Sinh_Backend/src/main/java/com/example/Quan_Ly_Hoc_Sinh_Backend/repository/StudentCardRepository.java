package com.example.Quan_Ly_Hoc_Sinh_Backend.repository;

import com.example.Quan_Ly_Hoc_Sinh_Backend.model.Entity.StudentCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentCardRepository extends JpaRepository<StudentCard, Long> {
    Optional<StudentCard> findByCardNumber(String cardNumber);
    Optional<StudentCard> findByStudentId(Long studentId);
}
