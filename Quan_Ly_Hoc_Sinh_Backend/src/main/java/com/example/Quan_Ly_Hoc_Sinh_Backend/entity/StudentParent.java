package com.example.Quan_Ly_Hoc_Sinh_Backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "student_parent")
public class StudentParent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_student_parent")
    private int idStudentParent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id_student_parent", nullable = false)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id_student_parent", nullable = false)
    private Parent parent;

    @Column(name = "relation_student_parent")
    private String relationStudentParent; // Father, Mother, Guardian


}
