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
@Table(name = "parent")
public class Parent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name", nullable = false, length = 100)
    private String fullName;

    // Thông tin đăng nhập (nếu phụ huynh có tài khoản riêng)
    @Column(name = "username", unique = true, length = 50)
    private String username;

    @Column(name = "password", length = 120)
    private String password; // Lưu hash

    @Temporal(TemporalType.DATE)
    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "phone_number", unique = true, length = 15)
    private String phoneNumber;

    @Column(name = "email", unique = true, length = 100)
    private String email;

    @Column(name = "occupation", length = 100)
    private String occupation; // Nghề nghiệp

    // Mối quan hệ N-M (Phụ huynh - Học sinh): Được quản lý bởi Entity trung gian StudentParent
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<StudentParent> childrenLinks;
}
