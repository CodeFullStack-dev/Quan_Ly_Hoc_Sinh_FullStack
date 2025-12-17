package com.example.Quan_Ly_Hoc_Sinh_Backend.model.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name", nullable = false, length = 100)
    private String fullName;

    // Thông tin đăng nhập
    @Column(name = "username", unique = true, nullable = false, length = 50)
    private String username;

    // Thêm vào trong class Employee
    @Column(name = "email", unique = true, length = 100)
    private String email;

    @Column(name = "password", nullable = false, length = 120)
    private String password; // Lưu hash

    @Temporal(TemporalType.DATE)
    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "phone_number", length = 15)
    private String phoneNumber;

    // Sử dụng Enum cho giới tính (cần tạo Enum EGender)
    // @Enumerated(EnumType.STRING)
    // private EGender gender;

    // Mối quan hệ N-1: Thuộc về một trường
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id", nullable = false)
    private School school;

    // Mối quan hệ N-N: Vai trò người dùng (Spring Security)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "employee_role",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;
}
