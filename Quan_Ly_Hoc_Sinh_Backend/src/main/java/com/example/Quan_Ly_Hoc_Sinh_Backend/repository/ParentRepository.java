package com.example.Quan_Ly_Hoc_Sinh_Backend.repository;

import com.example.Quan_Ly_Hoc_Sinh_Backend.model.Entity.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ParentRepository extends JpaRepository<Parent, Long> {
    Optional<Parent> findByPhoneNumber(String phoneNumber);
    // Repository
    @Query("SELECT p FROM Parent p WHERE " +
            "LOWER(p.fullName) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "p.phoneNumber LIKE CONCAT('%', :query, '%') OR " +
            "LOWER(p.email) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Parent> getAllParentsInfo(@Param("query") String query);
}
