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
@Table(name = "staff")
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_staff")
    private int idStaff;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id_staff",nullable = false)
    private School school;

    @Column(name = "full_name_staff", nullable = false)
    private String fullNameStaff;

    @Column(name = "dob_staff")
    private LocalDate dobStaff;

    @Column(name = "gender_staff")
    private String genderStaff;

    @Column(name = "phone_staff")
    private String phoneStaff;

    @Column(name = "email_staff")
    private String emailStaff;

    @Column(name = "address_staff")
    private String addressStaff;

    //Liên kết với tài khoản đăng nhập hệ thống
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id_staff")
    private User user;

    @OneToMany(mappedBy = "teacher", fetch = FetchType.LAZY,
            cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
    private List<TeacherAssignment> listTeacherAssignment;

    @OneToMany(mappedBy = "teacher", fetch = FetchType.LAZY,
            cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
    private List<LessonLog> listLessonLog;

    @OneToMany(mappedBy = "teacher", fetch = FetchType.LAZY,
            cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
    private List<ScoreRecord> listScoreRecord;

    @OneToMany(mappedBy = "homeroomTeacher", fetch = FetchType.LAZY,
            cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
    private List<Classroom> listHomeClasses;
}
