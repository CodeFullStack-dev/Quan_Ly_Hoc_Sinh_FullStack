package com.example.Quan_Ly_Hoc_Sinh_Backend.repository;

import com.example.Quan_Ly_Hoc_Sinh_Backend.model.Entity.Employee;
import com.example.Quan_Ly_Hoc_Sinh_Backend.model.Enum.Erole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // Rất quan trọng cho Spring Security
    Optional<Employee> findByUsername(String username);

    // Tìm kiếm giáo viên theo vai trò (ví dụ: tất cả giáo viên)
    List<Employee> findByRoles_Name(Erole roleName);
}
