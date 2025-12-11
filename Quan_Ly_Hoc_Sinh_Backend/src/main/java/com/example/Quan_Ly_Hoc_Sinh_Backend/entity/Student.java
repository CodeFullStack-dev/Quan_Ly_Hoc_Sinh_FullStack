package com.example.Quan_Ly_Hoc_Sinh_Backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_student")
    private int idStudent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id_student", nullable = false)
    private School school;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "classroom_id_student", nullable = false)
    private Classroom classroom;

    @Column(name="code_student", nullable = false, unique = true)
    private String codeStudent;

    @Column(name = "full_name_student", nullable = false)
    private String fullNameStudent;

    @Column(name = "dob_student")
    private LocalDate dobStudent;

    @Column(name = "gender_student")
    private String genderStudent;

    @Column(name = "phone_student")
    private String phoneStudent;

    @Column(name = "address_student")
    private String addressStudent;

    @OneToOne(mappedBy = "student", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private StudentCard studentCard;

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<StudentParent> listStudentParents;

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ScoreRecord> listScoreRecords;

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ReportCard> listReportCards;

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Attendance> listAttendances;
}
