package com.example.Quan_Ly_Hoc_Sinh_Backend.model.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "school")
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "school_name", nullable = false, length = 100)
    private String schoolName;

    @Column(name = "address", length = 255)
    private String address;

    @Column(name = "phone_number", length = 15)
    private String phoneNumber;

    @Column(name = "email", unique = true, length = 100)
    private String email;

    // Mối quan hệ 1-N: Một trường có nhiều nhân viên
    @OneToMany(mappedBy = "school", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Employee> employees;
    // Mối quan hệ 1-N: Một trường có nhiều lớp
    // @OneToMany(mappedBy = "school", cascade = CascadeType.ALL, orphanRemoval = true)
    // private List<SchoolClass> classes;
}
