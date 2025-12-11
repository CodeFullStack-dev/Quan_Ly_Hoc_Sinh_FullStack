package com.example.Quan_Ly_Hoc_Sinh_Backend.repository;

import com.example.Quan_Ly_Hoc_Sinh_Backend.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {
    Optional<Subject> findByNameSubject(String nameSubject);
}

