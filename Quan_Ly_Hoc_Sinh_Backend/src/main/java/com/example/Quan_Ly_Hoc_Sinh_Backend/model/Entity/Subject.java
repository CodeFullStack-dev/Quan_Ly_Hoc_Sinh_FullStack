package com.example.Quan_Ly_Hoc_Sinh_Backend.model.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "subject")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "subject_name", nullable = false, length = 100)
    private String subjectName; // Ví dụ: Toán, Văn, Lịch sử

    @Column(name = "subject_code", unique = true, length = 20)
    private String subjectCode;

    // Mối quan hệ N-1: Thuộc về một trường
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id", nullable = false)
    private School school;

    // Mối quan hệ N-N (Giáo viên - Môn học): Sử dụng Entity trung gian TeacherSubjectAssignment
    // Điều này giúp quản lý việc phân công giáo viên cho một môn học cụ thể trong một năm học nào đó.
    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TeacherSubjectAssignment> assignments;
}

