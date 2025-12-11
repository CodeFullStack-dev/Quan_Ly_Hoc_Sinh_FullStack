package com.example.Quan_Ly_Hoc_Sinh_Backend.repository;

import com.example.Quan_Ly_Hoc_Sinh_Backend.entity.Parent;
import com.example.Quan_Ly_Hoc_Sinh_Backend.entity.Student;
import com.example.Quan_Ly_Hoc_Sinh_Backend.entity.StudentParent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentParentRepository extends JpaRepository<StudentParent, Integer> {
    List<StudentParent> findByStudent(Student student);
    List<StudentParent> findByParent(Parent parent);
}
