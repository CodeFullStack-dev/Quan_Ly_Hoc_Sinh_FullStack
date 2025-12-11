package com.example.Quan_Ly_Hoc_Sinh_Backend.repository;

import com.example.Quan_Ly_Hoc_Sinh_Backend.entity.Staff;
import com.example.Quan_Ly_Hoc_Sinh_Backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Integer> {
    Optional<Staff> findByUser(User user); // Tìm Staff sau khi User đăng nhập

    // Đã sửa tên phương thức để khớp với thuộc tính 'fullNameStaff'
    List<Staff> findByFullNameStaffContaining(String keyword); // Tìm kiếm Nhân viên theo tên
}
