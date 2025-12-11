package com.example.Quan_Ly_Hoc_Sinh_Backend.repository;

import com.example.Quan_Ly_Hoc_Sinh_Backend.entity.Role;
import com.example.Quan_Ly_Hoc_Sinh_Backend.entity.User;
import com.example.Quan_Ly_Hoc_Sinh_Backend.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
    List<UserRole> findByUser(User user);
    Optional<UserRole> findByUserAndRole(User user, Role role);
}
