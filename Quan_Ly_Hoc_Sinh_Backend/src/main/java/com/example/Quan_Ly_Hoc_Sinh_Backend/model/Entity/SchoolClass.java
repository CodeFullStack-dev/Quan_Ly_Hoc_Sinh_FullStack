package com.example.Quan_Ly_Hoc_Sinh_Backend.model.Entity;

import com.example.Quan_Ly_Hoc_Sinh_Backend.model.Enum.EGradeLevel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "school_class")
public class SchoolClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "class_name", nullable = false, length = 50)
    private String className; // Ví dụ: 10A1, 12C3

    // Khối học (sử dụng Enum)
    @Enumerated(EnumType.STRING)
    @Column(name = "grade_level", nullable = false)
    private EGradeLevel gradeLevel;

    @Column(name = "school_year", nullable = false, length = 9)
    private String schoolYear; // Niên khóa, ví dụ: "2024-2025"

    // Mối quan hệ N-1: Thuộc về một trường
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id", nullable = false)
    private School school;

    // Mối quan hệ 1-1: Giáo viên chủ nhiệm (Chỉ định duy nhất)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "homeroom_teacher_id", unique = true)
    private Employee homeroomTeacher;

    // Mối quan hệ 1-N: Một lớp có nhiều học sinh
    @OneToMany(mappedBy = "schoolClass", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Student> students;
}
