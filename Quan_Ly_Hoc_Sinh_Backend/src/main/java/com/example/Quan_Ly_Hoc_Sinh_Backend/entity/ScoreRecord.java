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
@Table(name = "score_record")
public class ScoreRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_score_record")
    private int idScoreRecord;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id_score_record", nullable = false)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id_score_record", nullable = false)
    private Staff teacher;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "semester_id_score_record", nullable = false)
    private Semester semester;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "score_type_id_score_record", nullable = false)
    private ScoreType scoreType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id_score_record", nullable = false)
    private Subject subject;

    @Column(name = "score_record_value")
    private Double scoreRecordValue;
}
