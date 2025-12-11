package com.example.Quan_Ly_Hoc_Sinh_Backend.repository;

import com.example.Quan_Ly_Hoc_Sinh_Backend.entity.ScoreType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ScoreTypeRepository extends JpaRepository<ScoreType, Integer> {
    Optional<ScoreType> findByNameScoreType(String nameScoreType);
}
