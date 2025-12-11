package com.example.Quan_Ly_Hoc_Sinh_Backend.repository;

import com.example.Quan_Ly_Hoc_Sinh_Backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    // Tìm User theo username (dùng cho login)
    Optional<User> findByUsernameUser(String usernameUser);

    // Kiểm tra username đã tồn tại chưa (dùng cho đăng ký)
    boolean existsByUsernameUser(String usernameUser);
}
