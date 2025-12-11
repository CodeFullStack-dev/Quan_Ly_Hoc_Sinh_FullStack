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
@Table(name = "lesson_log")
public class LessonLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_lesson_log")
    private int idLessonLog;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "classroom_id_lesson_log", nullable = false)
    private Classroom classroom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id_lesson_log", nullable = false)
    private Subject subject;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id_lesson_log", nullable = false)
    private Staff teacher;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "semester_id_lesson_log", nullable = false)
    private Semester semester;

    @Column(name = "lesson_date_lesson_log")
    private LocalDate lessonDateLessonLog; //Nội dung bài học

    @Column(name = "content_lesson_log")
    private String contentLessonLog;
}

