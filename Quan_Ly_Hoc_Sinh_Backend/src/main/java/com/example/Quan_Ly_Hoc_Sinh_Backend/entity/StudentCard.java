package com.example.Quan_Ly_Hoc_Sinh_Backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "student_card")
public class StudentCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_student_card")
    private int idStudentCard;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id_student_card", nullable = false, unique = true)
    private Student student;

    @Column(name = "avatar_url_student_card")
    private String avatarUrlStudentCard;

    @Column(name = "issue_date_student_card")
    private LocalDate issueDateStudentCard;

    @Column(name = "expired_date_student_card")
    private LocalDate expiredDateStudentCard;
}
