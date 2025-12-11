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
@Table(name = "report_card")
public class ReportCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_report_card")
    private int idReportCard;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id_report_card", nullable = false)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "semester_id_report_card", nullable = false)
    private Semester semester;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conduct_id_report_card", nullable = false)
    private Conduct conduct;

    @Column(name = "gpa_report_card")
    private Double gpaReportCard;

    @Column(name = "comment_report_card")
    private String commentReportCard;
}
