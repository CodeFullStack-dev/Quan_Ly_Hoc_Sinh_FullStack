package com.example.Quan_Ly_Hoc_Sinh_Backend.repository;

import com.example.Quan_Ly_Hoc_Sinh_Backend.model.Entity.StudentCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StudentCardRepository extends JpaRepository<StudentCard, Long> {
    Optional<StudentCard> findByCardNumber(String cardNumber);

    @Query("SELECT sc FROM StudentCard sc WHERE sc.student.studentCode = :studentCode")
    Optional<StudentCard> findByStudentCode(@Param("studentCode") String studentCode);

    @Query("SELECT COUNT(sc) > 0 FROM StudentCard sc WHERE sc.student.studentCode = :studentCode")
    boolean existsByStudentCode(@Param("studentCode") String studentCode);
}
