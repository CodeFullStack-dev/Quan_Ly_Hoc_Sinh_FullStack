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
@Table(name = "attendance")
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_attendance")
    private int idAttendance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id_attendance", nullable = false)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "classroom_id_attendance", nullable = false)
    private Classroom classroom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "semester_id_attendance", nullable = false)
    private Semester semester;

    @Column(name = "date_attendance")
    private LocalDate dateAttendance;

    @Column(name = "status_attendance")
    private String statusAttendance; // PRESENT, ABSENT, LATE
}
