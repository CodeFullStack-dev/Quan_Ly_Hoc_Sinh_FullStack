package com.example.Quan_Ly_Hoc_Sinh_Backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "semester")
public class Semester {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_semester")
    private int idSemester;

    @Column(name = "name_semester", nullable = false)
    private String nameSemester;

    @Column(name = "academic_year_semester", nullable = false)
    private String academicYearSemester;

    @OneToMany(mappedBy = "semester", fetch = FetchType.LAZY,
        cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}
    )
    private List<ScoreRecord> listScoreRecords;

    @OneToMany(mappedBy = "semester", fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}
    )
    private List<ReportCard> listReportCards;

    @OneToMany(mappedBy = "semester", fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}
    )
    private  List<LessonLog> listLessonLogs;

    @OneToMany(mappedBy = "semester", fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}
    )
    private List<Attendance> listAttendances;

    @OneToMany(mappedBy = "semester", fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}
    )
    private List<TeacherAssignment> listTeacherAssignments;
}
