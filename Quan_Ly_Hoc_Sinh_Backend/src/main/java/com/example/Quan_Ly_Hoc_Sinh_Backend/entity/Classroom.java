package com.example.Quan_Ly_Hoc_Sinh_Backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "classroom")
public class Classroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_classroom")
    private int idClassroom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id_classroom", nullable = false)
    private School school;

    @Column(name = "name_classroom", nullable = false)
    private String nameClassroom;

    @Column(name = "grade_level_classroom")
    private String gradeLevelClassroom;

    @Column(name = "academic_year_classroom")
    private String academicYearClassroom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "homeroom_teacher_id_classroom")
    private Staff homeroomTeacher;

    @OneToMany(mappedBy = "classroom", fetch = FetchType.LAZY,
            cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH
    })
    private List<Student> listStudents;

    @OneToMany(mappedBy = "classroom", fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}
    )
    private List<LessonLog> listLessonLogs;

    @OneToMany(mappedBy = "classroom", fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}
    )
    private List<Attendance> listAttendances;

    @OneToMany(mappedBy = "classroom", fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}
    )
    private List<TeacherAssignment> listTeacherAssignments;
}
